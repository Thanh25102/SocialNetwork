package tech.mobile.social.data.repository
import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import tech.mobile.social.FriendSuggestQuery
import tech.mobile.social.UserQuery
import tech.mobile.social.UserprofileQuery
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.PageInfo
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.domain.repository.ProfileRepo
import java.util.Date

class ProfileRepoImpl(
    private val apolloClient: ApolloClient,
    pref: SharedPreferences
) : ProfileRepo {
    override suspend fun GetAllPost() : ApolloResponse<UserprofileQuery.Data>? {
        try {
            val result = apolloClient.query(UserprofileQuery(Optional.present(99)))
                .execute()
            if(result.hasErrors()) {
                val error = result.errors?.firstOrNull()
                Log.e("RepoImpl", "Requests: ${error?.message}")
                return null;
            }
            return result;
        } catch (e: ApolloException) {
            Log.e("RepoImpl", "Requests: ${e.message}")
            return null;
        }




    }
}