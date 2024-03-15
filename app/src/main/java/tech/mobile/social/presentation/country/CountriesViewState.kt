package tech.mobile.social.presentation.country

import tech.mobile.social.domain.model.country.DetailedCountry
import tech.mobile.social.domain.model.country.SimpleCountry

data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: DetailedCountry? = null
)