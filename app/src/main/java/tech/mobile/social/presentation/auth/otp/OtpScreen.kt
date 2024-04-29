package tech.mobile.social.presentation.auth.otp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OtpScreen(
    state: OtpState,
    actions: OtpActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Otp")
private fun OtpScreenPreview() {
    OtpScreen(
        state = OtpState(),
        actions = OtpActions()
    )
}

