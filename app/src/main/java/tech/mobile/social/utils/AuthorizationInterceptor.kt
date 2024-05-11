package tech.mobile.social.utils

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val prefs: SharedPreferences):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("Token","intercepted");
        val request = chain.request().newBuilder()
            .apply {
                prefs.getString("token", null)?.let { token ->
                    addHeader("Authorization", token)
                }
            }
            .build()
        return chain.proceed(request)
    }
}