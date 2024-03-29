package tech.mobile.social.presentation.app.profile

import tech.mobile.social.presentation.app.home.post.PostState


/**
 * UI State that represents ProfilesScreen
 **/
data class ProfilesState (val posts:List<PostState>)

/**
 * Profiles Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ProfilesActions(
    val onClick: () -> Unit = {}
)