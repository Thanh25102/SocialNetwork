package tech.mobile.social.data.repository

import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.CreatePostMutation
import tech.mobile.social.NewsfeedQuery
import tech.mobile.social.PostQuery
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.PageInfo
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.domain.repository.PostRepo
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
            it.node.content?.let { it1 ->
                Post(
                    it.node.id,
                    it1,
                    it.node.createdAt ?: Date(),
                    User(it.node.user.id, it.node.user.username),
                    it.node.file?.path,
                    it.node.isLike
                )
            }
        } ?: emptyList()

        val pageInfo = PageInfo(
            endCursor = results?.pageInfo?.endCursor ?: "",
            hasNextPage = results?.pageInfo?.hasNextPage ?: false
        )
        return Result.Success(Posts(nodes, pageInfo))

    }

    override suspend fun CreatePost(
        id: Optional<String?>,
        content: Optional<String?>,
        file: Optional<Any?>,
//        createdAt: LocalDateTime,
    ): ApolloResponse<CreatePostMutation.Data> {
        val result = apolloClient.mutation(
            CreatePostMutation(
                content,
                file,
                id,
            )
        ).execute()

        return result;
    }

    override suspend fun NewsFeed(
        take: Optional<Int?>,
        after: Optional<String?>
    ): ApolloResponse<NewsfeedQuery.Data>? {
        val result = apolloClient.query(
            NewsfeedQuery(
                take = take,
                after = after
            )
        ).execute()
        return result;
    }

}