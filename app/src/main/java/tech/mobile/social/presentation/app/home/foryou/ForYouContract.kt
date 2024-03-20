package tech.mobile.social.presentation.app.home.foryou

import tech.mobile.social.presentation.app.home.post.PostState
import java.time.LocalDateTime


/**
 * UI State that represents ForYouScreen
 **/
class ForYouState(val posts:List<PostState>) {
}



/**
 * ForYou Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ForYouActions(
    val onClick: () -> Unit = {},
)