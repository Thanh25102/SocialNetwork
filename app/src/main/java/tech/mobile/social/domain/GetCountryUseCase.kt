package tech.mobile.social.domain

class GetCountryUseCase(private val countryClient: CountryClient) {
    suspend fun execute(code: String) = countryClient.getCountry(code)
}