package tech.mobile.social.presentation.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents TestScreen
 **/
class TestState

/**
 * Test Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class TestActions(
    val onClick: () -> Unit = {}
)