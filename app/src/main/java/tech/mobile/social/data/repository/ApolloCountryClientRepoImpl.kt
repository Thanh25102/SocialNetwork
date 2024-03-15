package tech.mobile.social.data.repository

import com.apollographql.apollo3.ApolloClient
import tech.mobile.social.CountriesQuery
import tech.mobile.social.CountryQuery
import tech.mobile.social.data.toDetailedCountry
import tech.mobile.social.data.toSimpleCountry
import tech.mobile.social.domain.repository.CountryClientRepo
import tech.mobile.social.domain.model.country.DetailedCountry
import tech.mobile.social.domain.model.country.SimpleCountry

class ApolloCountryClientRepoImpl(
    private val apolloClient: ApolloClient
) : CountryClientRepo {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}