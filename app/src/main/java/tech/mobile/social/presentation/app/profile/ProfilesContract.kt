package tech.mobile.social.presentation.app.profile

import tech.mobile.social.presentation.app.post.PostState


/**
 * UI State that represents ProfilesScreen
 **/
data class ProfilesState(val posts: List<PostState?>? = emptyList())

/**
 * Profiles Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ProfilesActions(
    val onClick: () -> Unit = {},
    val onOpenFriend: () -> Unit = {},
    val onLogout: () -> Unit = {}
)
