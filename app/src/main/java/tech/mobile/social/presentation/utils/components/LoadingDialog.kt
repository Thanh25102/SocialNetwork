package tech.mobile.social.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import tech.mobile.social.presentation.auth.forgot.ForgotActions
import tech.mobile.social.presentation.auth.forgot.ForgotScreen
import tech.mobile.social.presentation.auth.forgot.ForgotState

@Composable
fun LoadingDialog(isLoading: Boolean, label: String = "Đang tải...") {
    if (isLoading) {
        Dialog(
            onDismissRequest = { /*TODO*/ },
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White),
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(modifier = Modifier.padding(10.dp))
                    Text(label, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }

}

@Composable
@Preview(name = "LoadingDialog")
private fun LoadingDialogPreview() {
    LoadingDialog(
        isLoading = true,
        label = "Đang đăng nhập..."
    )
}
