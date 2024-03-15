package tech.mobile.social.presentation.country

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.mobile.social.domain.repository.CountryClientRepo
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countryClientRepo: CountryClientRepo
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())


    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            _state.update {
                it.copy(
                    countries = countryClientRepo.getCountries(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code:String){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            _state.update {
                it.copy(
                    selectedCountry = countryClientRepo.getCountry(code),
                    isLoading = false
                )
            }
        }
    }

    fun dismissCountryDialog(){
        _state.update {
            it.copy(selectedCountry = null)
        }
    }


}