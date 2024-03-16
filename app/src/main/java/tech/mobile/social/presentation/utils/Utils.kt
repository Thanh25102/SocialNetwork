package tech.mobile.social.presentation.utils


fun validateInput(input: String, regex: String?): Boolean {
    if (regex == null || regex == "") return true
    return input.matches(Regex(regex))
}
