package tech.mobile.social.presentation.auth

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.R
import tech.mobile.social.Screens
import tech.mobile.social.presentation.utils.components.*


@Composable
fun RegisterScreen(navController: NavController) {
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
            InputApp(onValueChange = {}, label = "Họ và tên", regex = "^[a-zA-Z]\\w{5,}$")
            EmailApp(onValueChange = {})
            PasswordApp(
                onValueChange = {},
                regex = stringResource(R.string.regexPassword),
                errMsg = stringResource(R.string.errPasswordMsg)
            )
            PasswordApp(
                onValueChange = {},
                label = "Nhập lại mật khẩu",
                repeated = true,
                repeatedPassword = "",
                errMsg = stringResource(R.string.errPasswordRepeated)
            )
            BtnApp("Đăng ký")
            AuthTxtApp(
                "Đã có tài khoản? ",
                onClick = { navController.navigate(Screens.Login.route) },
                activeValue = "Đăng nhập"
            )
        }
    }
}


@Composable
@Preview(name = "RegisterScreenPrev", showBackground = true)
fun RegisterScreenPrev() {
    val nav = rememberNavController()
    RegisterScreen(nav)
}