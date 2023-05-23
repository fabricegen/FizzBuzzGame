package com.lv.fizzbuzzgame.ui.feature.displayresult

sealed interface DisplayResultUiState {

    object Init : DisplayResultUiState

    data class Success(val data: List<String>) : DisplayResultUiState
}
