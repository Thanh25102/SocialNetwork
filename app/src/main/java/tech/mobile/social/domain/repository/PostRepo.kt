package tech.mobile.social.domain.repository

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import java.time.LocalDateTime

interface PostRepo {
    suspend fun GetPosts(): Result<Posts, DataError.ServerErrors>
    suspend fun CreatePost(id: String,content: String, createdAt: LocalDateTime,createdBy: User): Result<Post, DataError.ServerErrors>
}