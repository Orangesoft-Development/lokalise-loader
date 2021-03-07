package util

internal fun <T, V, S> List<T>.leftJoin(other: List<V>, receiverSelector: (T) -> S, paramSelector: (V) -> S): Map<T, V?> {
    val resultMap = mutableMapOf<T, V?>()
    forEach { receiver ->
        val receiverItem = receiverSelector(receiver)
        val matchingItem = other.firstOrNull { paramSelector(it) == receiverItem }
        resultMap[receiver] = matchingItem
    }
    return resultMap
}