package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.CreatePostMutation
import tech.mobile.social.NewsfeedQuery
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.PostModel
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.domain.repository.PostRepo
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import tech.mobile.social.type.UserCreateNestedOneWithoutPostsInput
import java.time.LocalDateTime

class PostUseCaseImpl(private val postRepo: PostRepo) : PostUseCase {
    override suspend fun Getpost(): Result<Posts, DataError.ServerErrors> {
        return postRepo.GetPosts()
    }

    override suspend fun Createpost(id: Optional<String?>, content: String, createdAt: LocalDateTime): ApolloResponse<CreatePostMutation.Data> {
        return postRepo.CreatePost(id,content,createdAt)
    }

    override suspend fun NewsFeed(
        take: Optional<Int?>,
        after: Optional<String?>
    ): ApolloResponse<NewsfeedQuery.Data>? {
        return postRepo.NewsFeed(take, after)
    }
}