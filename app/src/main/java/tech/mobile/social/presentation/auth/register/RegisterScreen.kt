package tech.mobile.social.presentation.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.*
import tech.mobile.social.ui.theme.BtnColor
import tech.mobile.social.ui.theme.PrimaryColor

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
                contentDescription = stringResource(id = R.string.logo),
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 50.dp)
            )
            InputApp(onValueChange = { actions.onNameChange(it) }, label = "Họ và tên", regex = "^[a-zA-Z]\\w{5,}$")
            EmailApp(
                onValueChange = { actions.onEmailChange(it) },
                modifier = Modifier.padding(top = 10.dp)
            )
            PasswordApp(
                onValueChange = { actions.onPasswordChange(it) },
                errMsg = stringResource(R.string.errPasswordMsg),
                modifier = Modifier.padding(top = 10.dp)
            )
            PasswordApp(
                onValueChange = { actions.onPasswordConfirmChange(it) },
                label = "Nhập lại mật khẩu",
                repeated = true,
                repeatedPassword = state.password,
                errMsg = stringResource(R.string.errPasswordRepeated),
                modifier = Modifier.padding(top = 10.dp)
            )

            Button(
                onClick = { actions.onRegister() },
                colors = ButtonDefaults.buttonColors(BtnColor),
                contentPadding = PaddingValues(horizontal = 50.dp, vertical = 20.dp),
                modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Đăng ký")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Bạn đã có tài khoản ?")
                TextButton(onClick = { actions.goBack() }) {
                    Text(text = "Đăng nhập", color = PrimaryColor)
                }
            }
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

