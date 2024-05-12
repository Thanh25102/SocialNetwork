package tech.mobile.social.domain.model.post


data class User(val id: String, val username: String)

data class PageInfo(val endCursor: String, val hasNextPage: Boolean)
data class Posts(val posts: List<Post?>, val pageInfo: PageInfo)

data class Post(val id: String, val content: String, val createdAt: Any, val createdBy: User, val image: String? )

data class PostModel(
    val content: String
)