package tech.mobile.social.presentation.auth.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.EmailApp
import tech.mobile.social.presentation.utils.components.InputApp
import tech.mobile.social.presentation.utils.components.PasswordApp
import tech.mobile.social.ui.theme.BtnColor
import tech.mobile.social.ui.theme.PrimaryColor

@Composable
fun OtpScreen(
    state: OtpState,
    actions: OtpActions,
) {

    LaunchedEffect(key1 = state.isSuccess) {
        if (state.isSuccess) {
            actions.navLogin()
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

            if (state.isError) {
                Text(
                    text = state.erMess,
                    color = Color.Red
                )
            }

            InputApp(
                onValueChange = { actions.updateOtp(it) },
                imageVector = Icons.Filled.Key,
                label = "Mã xác nhận",
            )

            PasswordApp(
                onValueChange = { actions.updatePassword(it) },
                modifier = Modifier.padding(top = 10.dp)
            )

            PasswordApp(
                onValueChange = { actions.updatePasswordConfirm(it) },
                label = "Nhập lại mật khẩu",
                repeated = true,
                repeatedPassword = state.password,
                errMsg = stringResource(R.string.errPasswordRepeated),
                modifier = Modifier.padding(top = 10.dp)
            )

            Button(
                onClick = { actions.onResetPassword() },
                colors = ButtonDefaults.buttonColors(BtnColor),
                contentPadding = PaddingValues(horizontal = 50.dp, vertical = 20.dp),
                modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Đổi mật khẩu")
            }


        }
    }
}

@Composable
@Preview(name = "Otp")
private fun OtpScreenPreview() {
    OtpScreen(
        state = OtpState(),
        actions = OtpActions()
    )
}

