package com.lv.fizzbuzzgame.ui.common

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

    fun peek(): T = content
}

