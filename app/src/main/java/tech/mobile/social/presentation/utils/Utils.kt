package tech.mobile.social.presentation.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun validateInput(input: String, regex: String?): Boolean {
    if (regex == null || regex == "") return true
    return input.matches(Regex(regex))
}

fun formatTimeAgo(targetDateTime: LocalDateTime): String {
    val now = LocalDateTime.now()
    val seconds = ChronoUnit.SECONDS.between(targetDateTime, now)
    val minutes = ChronoUnit.MINUTES.between(targetDateTime, now)
    val hours = ChronoUnit.HOURS.between(targetDateTime, now)
    val days = ChronoUnit.DAYS.between(targetDateTime, now)
    val years = ChronoUnit.YEARS.between(targetDateTime, now)

    return when {
        seconds < 60 -> "Vừa xong"
        minutes < 60 -> "${minutes}p trước"
        hours < 24 -> "$hours giờ tract"
        days < 365 -> "$days ngày trước"
        else -> "$years năm trước"
    }
}
