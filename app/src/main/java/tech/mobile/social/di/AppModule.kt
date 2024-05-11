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
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CustomScalarAdapters
import tech.mobile.social.type.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val dateTimeAdapter = object : Adapter<LocalDateTime> {
    override fun fromJson(
        reader: JsonReader,
        customScalarAdapters: CustomScalarAdapters
    ): LocalDateTime {
        val stringDate = reader.nextString()
        return LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_DATE_TIME)
    }

    override fun toJson(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
        value: LocalDateTime
    ) {
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
        val customScalarAdapters = CustomScalarAdapters.Builder()
            .add(DateTime.type, dateTimeAdapter)
            .build()


        return ApolloClient.Builder()
            .serverUrl("http://171.239.144.144:8334/graphql")
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor(pref))
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


}

