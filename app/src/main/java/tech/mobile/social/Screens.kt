package tech.mobile.social

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Friends : Screens("friends")
    data object Post : Screens("post")
    data object Notification : Screens("notification")
    data object Profile : Screens("profile")

    data object Login : Screens("login")
    data object Register : Screens("register")
    data object ForgotPassword : Screens("forgot-password")

    data object Auth : Screens("auth")
    data object AppRoot : Screens("app")


    data object ForYou : Screens("for-you")
    data object Group : Screens("group")
    data object Following : Screens("following")

    data object Suggestion : Screens("suggestion")
    data object FriendRequest : Screens("friend-request")

}