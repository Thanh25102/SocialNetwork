package tech.mobile.social.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.mobile.social.data.repository.AuthorizeRepoImpl
import tech.mobile.social.domain.repository.AuthorizeRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://192.168.28.139:8334/graphql")
            .build()
    }


    @Provides
    @Singleton
    fun providesAuthorizeRepo(apolloClient: ApolloClient): AuthorizeRepo {
        return AuthorizeRepoImpl(apolloClient)
    }

}