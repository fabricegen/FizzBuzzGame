package com.lv.fizzbuzzgame.ui.domain.model

sealed interface GameResult {
    data class Success(val data: String): GameResult
    data class Error(val gameInputId: GameInputId): GameResult
}
