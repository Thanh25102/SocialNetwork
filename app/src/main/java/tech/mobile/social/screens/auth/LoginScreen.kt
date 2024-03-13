package tech.mobile.social.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tech.mobile.social.Screens
import tech.mobile.social.ui.theme.NavigationBarTheme

@Composable
fun LoginScreen(navController: NavController) {
    NavigationBarTheme {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .clip(MaterialTheme.shapes.large)
                ) {
                }
                Button(onClick = { navController.navigate(Screens.AppRoot.route) }) {
                    Text("Login")
                }
                Button(onClick = { navController.navigate(Screens.Register.route) }) {
                    Text("Register")
                }
                Button(onClick = { navController.navigate(Screens.ForgotPassword.route) }) {
                    Text("Forgot password")
                }
            }
        }
    }
}