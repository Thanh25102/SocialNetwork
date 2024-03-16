package tech.mobile.social.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.Screens

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = { navController.navigate(Screens.Login.route) }) {
                Text("Login")
            }
            Button(onClick = { navController.navigate(Screens.Register.route) }) {
                Text("Register")
            }
        }
    }
}

@Composable
@Preview(name = "ForgotPasswordPreview")
fun ForgotPasswordPrev() {
    ForgotPasswordScreen(navController = rememberNavController())
}