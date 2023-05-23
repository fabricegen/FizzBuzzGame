package com.lv.fizzbuzzgame.ui.feature.displayform

import com.lv.fizzbuzzgame.ui.domain.model.GameInputId

sealed interface DisplayFormUiState {

    object Init : DisplayFormUiState
    object InProgress : DisplayFormUiState
    data class Success(
        val data: String,
        val consumed: Boolean = false
    ) : DisplayFormUiState

    class Error(
        val gameInputId: GameInputId,
    ) : DisplayFormUiState
    class GeneralError(error: Throwable) : DisplayFormUiState
}
