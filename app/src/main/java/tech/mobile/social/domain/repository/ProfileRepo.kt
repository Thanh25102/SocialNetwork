package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.UserQuery
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.post.Posts

interface ProfileRepo {
    suspend fun GetAllPost(id: Optional<String?> ,take: Optional<Int?>, after: Optional<String?>): ApolloResponse<UserQuery.Data>?
}