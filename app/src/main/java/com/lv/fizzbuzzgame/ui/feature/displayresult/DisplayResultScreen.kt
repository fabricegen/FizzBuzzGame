package com.lv.fizzbuzzgame.ui.feature.displayresult

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lv.fizzbuzzgame.R

@Composable
fun DisplayResultRoute(
    modifier: Modifier = Modifier,
    viewModel: DisplayResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DisplayResultScreen(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primaryContainer),
        uiState = uiState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DisplayResultScreen(
    modifier: Modifier = Modifier,
    uiState: DisplayResultUiState
) {
    if (uiState is DisplayResultUiState.Success) {
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    title = {
                        Text(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            text = stringResource(id = R.string.app_name)
                        )
                    })
            }
        ) {
            LazyColumn {
                items(uiState.data.toMutableStateList()) { data: String ->
                    Row(
                        modifier = modifier.padding(16.dp)
                    ) {
                        Text(
                            modifier = modifier.weight(0.4f),
                            style = MaterialTheme.typography.headlineSmall,
                            text = data
                        )
                    }
                }
            }
        }
    }
}
