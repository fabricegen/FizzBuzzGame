package com.lv.fizzbuzzgame.ui.feature.displayform

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.lv.fizzbuzzgame.ui.common.dispatchers.CoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@Stable
@HiltViewModel
class NamesViewModel @Inject constructor(
    private val dispatchers: CoroutineDispatchers
) : ViewModel() {
    private val _uiState = MutableStateFlow(NamesUiState(names = names()))
    val uiState = _uiState as StateFlow<NamesUiState>

    private fun names(): List<String> {
        return mutableListOf(
            "jean",
            "david",
            "andre"
        ).toList()
    }

    private suspend fun postUiState(state: NamesUiState) {
        _uiState.emit(state)
    }

    fun addName() {
        val newList = _uiState.value.names.toMutableList()
        newList.add("Rene")
        _uiState.value = _uiState.value.copy(names = newList)
    }

    fun handleNameClick() {

    }
}
