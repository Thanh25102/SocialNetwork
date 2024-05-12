package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.UserQuery
import tech.mobile.social.UserprofileQuery
import tech.mobile.social.domain.repository.ProfileRepo
import tech.mobile.social.domain.usecase.interfaces.ProfileUseCase

class ProfileUseCaseImpl(private val profilerepo:ProfileRepo):ProfileUseCase {
    override suspend fun Getallpost(): ApolloResponse<UserprofileQuery.Data>? {
       return  profilerepo.GetAllPost()
    }

}