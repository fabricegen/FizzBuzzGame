package com.lv.fizzbuzzgame.ui.domain.usecase

import com.lv.fizzbuzzgame.ui.domain.model.GameInputData
import com.lv.fizzbuzzgame.ui.domain.model.GameResult

interface ComputeGameUseCase {
    suspend operator fun invoke(gameInputData: GameInputData): GameResult
}
