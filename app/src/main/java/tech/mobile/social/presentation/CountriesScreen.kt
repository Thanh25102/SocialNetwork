package tech.mobile.social.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.domain.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CoutriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxHeight()) {
        if (state.isLoading) {
            // Loading'
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            // Content
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable{onSelectCountry(country.code)}
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = country.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = country.name,
                fontSize = 24.sp
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(text = country.code)
        }
    }

}
