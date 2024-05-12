package tech.mobile.social.presentation.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.EmailApp
import tech.mobile.social.presentation.utils.components.PasswordApp
import tech.mobile.social.shared.UserState
import tech.mobile.social.ui.theme.BtnColor
import tech.mobile.social.ui.theme.PrimaryColor

import com.google.firebase.ktx.Firebase
import tech.mobile.social.presentation.utils.components.DividerWithText

@Composable
fun LoginScreen(
    state: CombinedUiState,
    actions: LoginActions,
    onLoginGoogle: () -> Unit,
    email: String = ""
) {

    LaunchedEffect(key1 = Unit) {
        if (email != "") {
            actions.onEmailChange(email)
        }
    }

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
            EmailApp(text = email, onValueChange = { actions.onEmailChange(it) })
            PasswordApp(
                onValueChange = { actions.onPasswordChange(it) },
                modifier = Modifier.padding(top = 10.dp)
            )
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

            Row(
                modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { actions.navRegister() }) {
                    Text(text = "Quên mật khẩu", color = PrimaryColor)
                }
            }

            DividerWithText("Đăng nhập với cách khác")

            Button(
                onClick = {
                    onLoginGoogle()
                },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 10.dp)
                    .border(
                        border = BorderStroke(width = 1.dp, MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp),
                    ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = stringResource(id = R.string.logo),
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
                )

            }


        }
    }
}


