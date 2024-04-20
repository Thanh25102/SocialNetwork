package tech.mobile.social.presentation.auth.forgot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.EmailApp
import tech.mobile.social.presentation.utils.components.InputApp
import tech.mobile.social.presentation.utils.components.PasswordApp
import tech.mobile.social.ui.theme.BtnColor
import tech.mobile.social.ui.theme.PrimaryColor
import androidx.compose.material.icons.filled.*

@Composable
fun ForgotScreen(
    state: ForgotState,
    actions: ForgotActions,
) {

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

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                OutlinedTextField(
//                    label = { Text(text = "Email") },
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                )
                Button(
                    onClick = { },
//                    colors = ButtonDefaults.buttonColors(BtnColor),
//                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
//                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
//                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Xác nhận")
                }
            }

            InputApp(
                onValueChange = { },
                imageVector = Icons.Filled.Key,
                label = "Mã OTP",
            )

            InputApp(
                onValueChange = { },
                imageVector = Icons.Filled.Password,
                label = "Mật khẩu mới",
            )

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(BtnColor),
                contentPadding = PaddingValues(horizontal = 50.dp, vertical = 20.dp),
                modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Xác nhận")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Bạn chưa có tài khoản ?")
                TextButton(onClick = { }) {
                    Text(text = "Đăng ký", color = PrimaryColor)
                }
            }

        }
    }
}

@Composable
@Preview(name = "Forgot")
private fun ForgotScreenPreview() {
    ForgotScreen(
        state = ForgotState(),
        actions = ForgotActions()
    )
}

