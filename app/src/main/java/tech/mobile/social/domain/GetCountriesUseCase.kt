package tech.mobile.social.domain

class GetCountriesUseCase(private val countryClient:CountryClient) {
    suspend fun execute() = countryClient.getCountries().sortedBy { it.name }
}
