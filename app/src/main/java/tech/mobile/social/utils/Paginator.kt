package tech.mobile.social.utils

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}