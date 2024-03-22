package tech.mobile.social.domain.model.post

import java.time.LocalDateTime


data class User(val id: String, val username: String)

data class PageInfo(val endCursor: String, val hasNextPage: Boolean)
data class Posts(val posts: List<Post>, val pageInfo: PageInfo)
data class Post(val id: String, val content: String, val createdAt: LocalDateTime, val createdBy: User)
