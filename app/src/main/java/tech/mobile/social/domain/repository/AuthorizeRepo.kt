package tech.mobile.social.domain.repository

import tech.mobile.social.domain.model.auth.Auth

interface AuthorizeRepo {
    suspend fun getAuthorize(username:String,password:String): Auth
}