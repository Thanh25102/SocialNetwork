package tech.mobile.social.domain.usecase.impl

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.PostModel
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.domain.repository.PostRepo
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import java.time.LocalDateTime

class PostUseCaseImpl(private val postRepo : PostRepo) : PostUseCase {
    override suspend fun Getpost(): Result<Posts, DataError.ServerErrors> {
        return postRepo.GetPosts()
    }

    override suspend fun Createpost(id: String, content: String, createdAt: LocalDateTime, createdBy: User): Result<Post, DataError.ServerErrors> {
        return postRepo.CreatePost(id,content,createdAt,createdBy)
    }
}