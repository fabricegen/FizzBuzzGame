package com.lv.fizzbuzzgame.ui.common

/**
 * Wrapper class around T
 * Used to filter emit last value from `StateFlow` on resubscribe
 */
class SingleFlowEvent<out T>(private val content: T) {
    private var consumed = false

    fun get(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }
}

