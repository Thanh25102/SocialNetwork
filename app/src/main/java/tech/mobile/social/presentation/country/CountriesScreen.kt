package tech.mobile.social.presentation.country

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.mobile.social.presentation.country.components.CountryItem
import tech.mobile.social.presentation.utils.components.LoadingDialog

@Composable
fun CountriesScreen() {

}

@Composable
fun CountriesContent(
    state: CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxHeight()) {
        LoadingDialog(isLoading = state.isLoading)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.countries) {
                CountryItem(
                    country = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onSelectCountry(it.code) }
                        .padding(16.dp),
                )
            }
        }
    }
}


