package tech.mobile.social.data.repository

import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.Create_postMutation
import tech.mobile.social.PostQuery
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.PageInfo
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.domain.repository.PostRepo
import tech.mobile.social.type.PostCreateInput
import tech.mobile.social.type.UserCreateNestedOneWithoutPostsInput
import java.time.LocalDateTime
import java.util.*

class PostRepoImpl(
    private val apolloClient: ApolloClient,
    pref: SharedPreferences
) : PostRepo {
    override suspend fun GetPosts(): Result<Posts, DataError.ServerErrors> {
        val results = apolloClient.query(PostQuery(take = Optional.Present(10)))
            .execute()
            .data?.posts
        val nodes = results?.edges?.map {
            Post(
                it.node.id,
                it.node.content,
                it.node.createdAt ?: Date(),
                User(it.node.user.id, it.node.user.username),
                it.node.file?.path
            )
        } ?: emptyList()

        val pageInfo = PageInfo(
            endCursor = results?.pageInfo?.endCursor ?: "",
            hasNextPage = results?.pageInfo?.hasNextPage ?: false
        )
        return Result.Success(Posts(nodes, pageInfo))

    }

    override suspend fun CreatePost(
        id: Optional<String?>,
        content: String,
        createdAt: LocalDateTime,
    ): ApolloResponse<Create_postMutation.Data> {
        val result = apolloClient.mutation(
            Create_postMutation(
                PostCreateInput(
                    content = content
                )
            )
        ).execute()

        return result;
    }




}