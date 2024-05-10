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
import tech.mobile.social.utils.AuthorizationInterceptor
import javax.inject.Singleton
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.network.http.DefaultHttpEngine
import com.apollographql.apollo3.network.ws.GraphQLWsProtocol
import com.apollographql.apollo3.network.ws.SubscriptionWsProtocol
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport
import com.apollographql.apollo3.network.ws.WsFrameType
import com.apollographql.apollo3.network.ws.WsProtocol
import tech.mobile.social.data.repository.CommentRepoImpl

import tech.mobile.social.data.repository.FriendRequestRepoImpl
import tech.mobile.social.data.repository.FriendSuggestRepoImpl
import tech.mobile.social.data.repository.NotificationRepoImpl
import tech.mobile.social.domain.repository.FriendRequestRepo

import tech.mobile.social.data.repository.PostRepoImpl
import tech.mobile.social.domain.repository.CommentRepo
import tech.mobile.social.domain.repository.FriendSuggestRepo
import tech.mobile.social.domain.repository.NotificationRepo
import tech.mobile.social.domain.repository.PostRepo
import tech.mobile.social.domain.usecase.impl.*
import tech.mobile.social.domain.usecase.interfaces.*
import tech.mobile.social.type.Comment

import tech.mobile.social.type.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val dateTimeAdapter = object : Adapter<LocalDateTime> {
    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters): LocalDateTime {
        val stringDate = reader.nextString()
        return LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_DATE_TIME)
    }

    override fun toJson(writer: JsonWriter, customScalarAdapters: CustomScalarAdapters, value: LocalDateTime) {
        val stringDate = value.format(DateTimeFormatter.ISO_DATE_TIME)
        writer.value(stringDate)
    }
}


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
        val accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2MmQ1NjYwZWU1ZWUyZTY1NjBmMzg1NSIsInVzZXJuYW1lIjoia2lkcDJoIiwiZW1haWwiOiJraWRwMmhAZ21haWwuY29tIiwiaWF0IjoxNzE0NjQ2OTAxLCJleHAiOjE3MjM2NDY5MDF9.yQxz419tB3FJBC9enlr4dbivaY3XgWjTAZyWGipWkdc";
        val customScalarAdapters = CustomScalarAdapters.Builder()
            .add(DateTime.type, dateTimeAdapter)
            .build()
        val httpEngine = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(pref))
            .build()

        return ApolloClient.Builder()
            .serverUrl("http://171.239.144.144:8334/graphql")
            .httpEngine(
                DefaultHttpEngine(httpEngine)
            )
            .subscriptionNetworkTransport(
                WebSocketNetworkTransport.Builder()
                    .protocol(GraphQLWsProtocol.Factory())
                    .okHttpClient(httpEngine)
                    .serverUrl("ws://171.239.144.144:8334/graphql")

                    .build()
            )

            .customScalarAdapters(customScalarAdapters)
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

    @Provides
    @Singleton
    fun providesCommentRepo(apolloClient: ApolloClient,pref: SharedPreferences) : CommentRepo {
        return CommentRepoImpl(apolloClient,pref)
    }

    @Provides
    @Singleton
    fun providesFriendRequestRepo(apolloClient: ApolloClient, pref: SharedPreferences): FriendRequestRepo {
        return FriendRequestRepoImpl(apolloClient, pref)
    }

    @Provides
    @Singleton
    fun providesFriendRequestUseCase(friendRequestRepo: FriendRequestRepo): FriendRequestUseCase {
        return FriendRequestUseCaseImpl(friendRequestRepo)
    }

    @Provides
    @Singleton
    fun providesFriendSuggestRepo(apolloClient: ApolloClient, pref: SharedPreferences): FriendSuggestRepo {
        return FriendSuggestRepoImpl(apolloClient, pref)
    }

    @Provides
    @Singleton
    fun providesFriendSuggestUseCase(friendSuggestRepo: FriendSuggestRepo): FriendSuggestUseCase {
        return FriendSuggestUseCaseImpl(friendSuggestRepo)
    }

    @Provides
    @Singleton
    fun providesNotificationRepo(apolloClient: ApolloClient, pref: SharedPreferences): NotificationRepo {
        return NotificationRepoImpl(apolloClient, pref)
    }

    @Provides
    @Singleton
    fun providesNotificationUseCase(notificationRepo: NotificationRepo): NotificationUseCase {
        return NotificationUseCaseImpl(notificationRepo)
    }

    @Provides
    @Singleton
    fun providesPostUseCase(postRepo : PostRepo) : PostUseCase{
        return PostUseCaseImpl(postRepo)
    }

    @Provides
    @Singleton
    fun providesRepo(apolloClient: ApolloClient,pref: SharedPreferences) : PostRepo{
        return PostRepoImpl(apolloClient,pref)
    }



    @Provides
    @Singleton
    fun providesCommentUseCase(commentRepo: CommentRepo) : CommentUseCase{
        return CommentUseCaseImpl(commentRepo)
    }

}

