package tech.mobile.social.domain.usecase.interfaces

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.PostModel
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import java.time.LocalDateTime

interface PostUseCase {
    suspend fun Getpost(): Result<Posts, DataError.ServerErrors>

    suspend fun Createpost(id: String,content: String,createdAt: LocalDateTime,createdBy: User) :Result<Post, DataError.ServerErrors>
}