package tech.mobile.social.presentation.auth.login2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.AuthTxtApp
import tech.mobile.social.presentation.utils.components.BtnApp
import tech.mobile.social.presentation.utils.components.EmailApp
import tech.mobile.social.presentation.utils.components.PasswordApp

@Composable
fun Login2Screen(
    state: Login2State,
    actions: Login2Actions,
) {
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
            EmailApp(onValueChange = { actions.onEmailChange(it) })
            PasswordApp(onValueChange = { actions.onPasswordChange(it) })
            BtnApp("Đăng nhập", onClick = { actions.onLogin() })
            AuthTxtApp("Chưa có tài khoản? ", onClick = { }, activeValue = "Đăng ký")
        }
    }
}

@Composable
@Preview(name = "Login2")
private fun Login2ScreenPreview() {
    Login2Screen(
        state = Login2State(),
        actions = Login2Actions()
    )
}

