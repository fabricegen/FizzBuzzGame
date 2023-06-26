package com.lv.fizzbuzzgame.ui.feature.displayform

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lv.fizzbuzzgame.ui.common.ComposeLifecycleObserver

@Composable
fun NamesRoute() {
    val viewModel: NamesViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // utilisation de remember pour les smarts recompositions
//    val onButtonClick = remember(viewModel) { { viewModel.addName() } }
//    val onNameClick = remember(viewModel) { { viewModel.handleNameClick() } }

// OU l'utilisation methods references
    val onButtonClick = viewModel::addName
    val onNameClick = viewModel::handleNameClick

    NameColumnWithButton(
        names = state.names,
        onButtonClick = onButtonClick,
        onNameClick = onNameClick,
    )

    ComposeLifecycleObserver(
        onStart = viewModel::onScreenLoaded,
        onStop = viewModel::onScreenStopped
    )
}

@Composable
fun NameColumnWithButton(
    names: List<String>,
    onButtonClick: () -> Unit,
    onNameClick: () -> Unit,
) {
    Column {
        names.forEach {
            CompositionTrackingName(name = it, onClick = onNameClick)
        }
        Button(onClick = onButtonClick) { Text("Add a Name") }
    }
}

@Composable
fun CompositionTrackingName(name: String, onClick: () -> Unit) {
    Log.e("*******COMPOSED", name)
    Text(name, modifier = Modifier.clickable(onClick = onClick))
}
