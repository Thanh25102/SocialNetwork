package tech.mobile.social.presentation.auth.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.AuthTxtApp
import tech.mobile.social.presentation.utils.components.BtnApp
import tech.mobile.social.presentation.utils.components.EmailApp
import tech.mobile.social.presentation.utils.components.PasswordApp
import tech.mobile.social.shared.UserState
import tech.mobile.social.ui.theme.BtnColor
import tech.mobile.social.ui.theme.NavigationBarTheme
import tech.mobile.social.ui.theme.PrimaryColor

@Composable
fun LoginScreen(
    state: CombinedUiState,
    actions: LoginActions,
) {
    Log.i("STATE", state.userState.toString())
    Log.i("STATE", state.loginState.toString())

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.logo),
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 50.dp)
            )
            EmailApp(onValueChange = { actions.onEmailChange(it) })
            PasswordApp(onValueChange = { actions.onPasswordChange(it) }, modifier = Modifier.padding(top = 10.dp))
            Button(
                onClick = { actions.onLogin() },
                colors = ButtonDefaults.buttonColors(BtnColor),
                contentPadding = PaddingValues(horizontal = 50.dp, vertical = 20.dp),
                modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Đăng nhập")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Bạn chưa có tài khoản ?")
                TextButton(onClick = { actions.navRegister() }) {
                    Text(text = "Đăng ký", color = PrimaryColor)
                }
            }

        }
    }
}

@Composable
@Preview(name = "Login2")
fun Login2ScreenPreview() {
    LoginScreen(
        state = CombinedUiState(LoginState(), UserState()),
        actions = LoginActions()
    )
}

