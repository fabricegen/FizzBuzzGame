package com.lv.fizzbuzzgame.ui.domain.usecase

import com.lv.fizzbuzzgame.ui.domain.model.GameInputData
import com.lv.fizzbuzzgame.ui.domain.model.GameInputId
import com.lv.fizzbuzzgame.ui.domain.model.GameResult

class ComputeGameUseCaseImpl : ComputeGameUseCase {

    override suspend operator fun invoke(gameInputData: GameInputData): GameResult {
        val result = checkInputData(gameInputData)
        return result ?: computeGame(gameInputData)
    }

    private fun computeGame(gameInputData: GameInputData): GameResult.Success {
        val firstInteger = gameInputData.firstInteger.toLong()
        val secInteger = gameInputData.secInteger.toLong()
        val limitInteger = gameInputData.limit.toInt()

        val data = arrayOfNulls<String>(limitInteger)
        for (value in 1..limitInteger) {
            val firstBySec = firstInteger * secInteger
            data[value-1] =
                if (value >= firstBySec && firstBySec % value == 0L) {
                    "${gameInputData.firstString}${gameInputData.secString}"
                } else if (value >= firstInteger && value % firstInteger == 0L) {
                    gameInputData.firstString
                } else if (value >= secInteger && value % secInteger == 0L) {
                    gameInputData.secString
                } else value.toString()
        }
        val result = data.joinToString(separator = ",")
        return GameResult.Success(result)
    }

    private fun checkInputData(gameInputData: GameInputData): GameResult.Error? {
        return when {
            gameInputData.firstString.trim().isEmpty() -> GameResult.Error(GameInputId.FIRST_STRING)
            gameInputData.secString.trim().isEmpty() -> GameResult.Error(GameInputId.SEC_STRING)
            gameInputData.firstInteger.toLongOrNull() == null -> GameResult.Error(GameInputId.FIRST_INTEGER)
            gameInputData.secInteger.toLongOrNull() == null -> GameResult.Error(GameInputId.SEC_INTEGER)
            gameInputData.limit.toIntOrNull() == null -> GameResult.Error(GameInputId.LIMIT)
            else -> null
        }
    }
}
