package tech.mobile.social.presentation.app.home.group


/**
 * UI State that represents GroupScreen
 **/
class GroupState

/**
 * Group Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class GroupActions(
    val onClick: () -> Unit = {}
)