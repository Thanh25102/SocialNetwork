package tech.mobile.social.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import tech.mobile.social.data.repository.AuthorizeRepoImpl
import tech.mobile.social.domain.repository.AuthorizeRepo
import tech.mobile.social.domain.usecase.impl.AuthUseCaseImpl
import tech.mobile.social.domain.usecase.interfaces.AuthUseCase
import tech.mobile.social.utils.AuthorizationInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideApolloClient(pref: SharedPreferences): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://192.168.1.5:8334/graphql")
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor(pref))
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesAuthorizeRepo(apolloClient: ApolloClient, pref: SharedPreferences): AuthorizeRepo {
        return AuthorizeRepoImpl(apolloClient, pref)
    }

    @Provides
    @Singleton
    fun providesAuthorizeUseCase(authorizeRepo: AuthorizeRepo): AuthUseCase {
        return AuthUseCaseImpl(authorizeRepo)
    }


}