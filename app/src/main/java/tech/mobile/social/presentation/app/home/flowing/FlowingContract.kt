package tech.mobile.social.presentation.app.home.flowing


/**
 * UI State that represents FlowingScreen
 **/
class FlowingState

/**
 * Flowing Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FlowingActions(
    val onClick: () -> Unit = {}
)