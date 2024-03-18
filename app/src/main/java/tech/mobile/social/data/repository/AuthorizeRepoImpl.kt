package tech.mobile.social.data.repository

import com.apollographql.apollo3.ApolloClient
import tech.mobile.social.AuthorizeMutation
import tech.mobile.social.data.toAuth
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.repository.AuthorizeRepo
import tech.mobile.social.type.AuthorizeInput

class AuthorizeRepoImpl(
    private val apolloClient: ApolloClient
) : AuthorizeRepo {
    override suspend fun getAuthorize(username: String, password: String): Auth {
        return apolloClient
            .mutation(AuthorizeMutation(AuthorizeInput(username, password)))
            .execute()
            .data?.toAuth()
            ?: Auth("", User(""))
    }
}