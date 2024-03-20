package tech.mobile.social.utils

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val prefs: SharedPreferences):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                prefs.getString("jwt", null)?.let { token ->
                    addHeader("Authorization", token)
                }
            }
            .build()
        return chain.proceed(request)
    }
}