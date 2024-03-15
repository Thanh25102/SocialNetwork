package tech.mobile.social.domain.repository

import tech.mobile.social.domain.model.country.DetailedCountry
import tech.mobile.social.domain.model.country.SimpleCountry


interface CountryClientRepo {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}