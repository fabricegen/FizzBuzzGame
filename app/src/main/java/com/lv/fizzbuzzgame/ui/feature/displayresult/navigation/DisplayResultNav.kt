package com.lv.fizzbuzzgame.ui.feature.displayresult.navigation

import android.net.Uri
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lv.fizzbuzzgame.ui.feature.displayresult.DisplayResultRoute

internal const val displayResultRoute = "display_result_route"

@VisibleForTesting
internal const val dataArg = "dataArg"

class DisplayResultArgs(val data: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(
            checkNotNull(savedStateHandle[dataArg]) as String
        )
}

fun NavController.navigateToDisplayResult(data: String) {
    val encodedData = Uri.encode(data)
    this.navigate("$displayResultRoute/$encodedData")
}

fun NavGraphBuilder.displayResultScreen() {
    composable(
        route = "$displayResultRoute/{$dataArg}",
        arguments = listOf(
            navArgument(dataArg) { type = NavType.StringType },
        )
    ) {
        DisplayResultRoute()
    }
}
