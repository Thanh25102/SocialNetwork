package tech.mobile.social

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import tech.mobile.social.navigation.RootNavigation
import tech.mobile.social.ui.theme.NavigationBarTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationBarTheme {
//                val viewModel = hiltViewModel<CountriesViewModel>()
//                val state by viewModel.state.collectAsState()
//                CountriesScreen(
//                    state = state,
//                    onSelectCountry = viewModel::selectCountry,
//                    onDismissCountryDialog = {},
//                )
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    RootNavigation()
                }
            }

        }
    }
}