package tech.mobile.social.data

import com.apollographql.apollo3.ApolloClient
import tech.mobile.social.CountriesQuery
import tech.mobile.social.CountryQuery
import tech.mobile.social.domain.CountryClient
import tech.mobile.social.domain.DetailedCountry
import tech.mobile.social.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {

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