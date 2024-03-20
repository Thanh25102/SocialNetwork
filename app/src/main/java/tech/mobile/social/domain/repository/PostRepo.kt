package tech.mobile.social.domain.repository

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts

interface PostRepo {
    suspend fun getPosts(): Result<Posts, DataError.ServerErrors>
    suspend fun createPost(post: Post): Result<Post, DataError.ServerErrors>
}