package tech.mobile.social.presentation.auth.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

@Composable
fun LoginScreen(
    state: CombinedUiState,
    actions: LoginActions,
) {
    Log.i("STATE", state.userState.toString())
    Log.i("STATE", state.loginState.toString())

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(MaterialTheme.colorScheme.background),
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
                contentDescription = stringResource(id = R.string.logo)
            )
            if (state.userState.errMsg != null) {
                Text(
                    text = state.userState.errMsg ?: "Đã có lỗi xảy ra vui lòng thử lại",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            EmailApp(onValueChange = { actions.onEmailChange(it) })
            PasswordApp(onValueChange = { actions.onPasswordChange(it) })
            BtnApp("Đăng nhập", onClick = { actions.onLogin() })
            AuthTxtApp("Chưa có tài khoản? ", onClick = { actions.navRegister() }, activeValue = "Đăng ký")
        }
    }
}

@Composable
@Preview(name = "Login2")
private fun Login2ScreenPreview() {
    LoginScreen(
        state = CombinedUiState(LoginState(), UserState()),
        actions = LoginActions()
    )
}

