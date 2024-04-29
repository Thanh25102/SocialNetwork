package tech.mobile.social.data.repository

import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import tech.mobile.social.PostQuery
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.PageInfo
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.domain.repository.PostRepo
import java.time.LocalDateTime

class PostRepoImpl(
    private val apolloClient: ApolloClient,
    pref: SharedPreferences
) : PostRepo {
    override suspend fun GetPosts(): Result<Posts, DataError.ServerErrors> {
        val results = apolloClient.query(PostQuery(take = 10.0))
            .execute()
            .data?.posts
        val nodes = results?.edges?.map {
            Post(
                it.node.id,
                it.node.content,
                it.node.createdAt ?: LocalDateTime.now(),
                User(it.node.user.id, it.node.user.username)
            )
        } ?: emptyList()

        val pageInfo = PageInfo(
            endCursor = results?.pageInfo?.endCursor ?: "",
            hasNextPage = results?.pageInfo?.hasNextPage ?: false
        )
        return Result.Success(Posts(nodes, pageInfo))

    }

    override suspend fun CreatePost(
        id: String,
        content: String,
        createdAt: LocalDateTime,
        createdBy: User
    ): Result<Post, DataError.ServerErrors> {
        TODO("Not yet implemented")
    }




}