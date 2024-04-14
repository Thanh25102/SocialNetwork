package tech.mobile.social.presentation.auth.register

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
import tech.mobile.social.presentation.utils.components.*

@Composable
fun RegisterScreen(
    state: RegisterState,
    actions: RegisterActions,
) {
    
    // TODO UI Rendering
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
            InputApp(onValueChange = { actions.onNameChange(it) }, label = "Họ và tên", regex = "^[a-zA-Z]\\w{5,}$")
            EmailApp(onValueChange = { actions.onEmailChange(it) })
            PasswordApp(
                onValueChange = { actions.onPasswordChange(it) },
//                regex = stringResource(R.string.regexPassword),
                errMsg = stringResource(R.string.errPasswordMsg)
            )
            PasswordApp(
                onValueChange = { actions.onPasswordConfirmChange(it) },
                label = "Nhập lại mật khẩu",
                repeated = true,
                repeatedPassword = state.password,
                errMsg = stringResource(R.string.errPasswordRepeated)
            )
            BtnApp("Đăng ký", onClick = { actions.onRegister() })
            AuthTxtApp(
                "Đã có tài khoản? ",
//                onClick = { navController.navigate(Screens.Login.route) },
                activeValue = "Đăng nhập"
            )
        }
    }
}

@Composable
@Preview(name = "Register")
private fun RegisterScreenPreview() {
    RegisterScreen(
        state = RegisterState(),
        actions = RegisterActions()
    )
}

