package tech.mobile.social.utils

import com.apollographql.apollo3.api.Error

fun handleException(errors: List<Error>): List<String> {
    val fields = errors.map { it.nonStandardFields }
    val map = fields?.get(0) as? Map<String, Any>
    if (map != null) {
        val messages = map["messages"] as List<String>
        return messages
    }
    return listOf("Đã có lỗi xảy ra")
}
