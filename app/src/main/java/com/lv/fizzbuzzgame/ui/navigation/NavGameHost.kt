package com.lv.fizzbuzzgame.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lv.fizzbuzzgame.ui.feature.displayform.navigation.displayFormRoute
import com.lv.fizzbuzzgame.ui.feature.displayform.navigation.displayFormScreen
import com.lv.fizzbuzzgame.ui.feature.displayresult.navigation.displayResultScreen
import com.lv.fizzbuzzgame.ui.feature.displayresult.navigation.navigateToDisplayResult

@Composable
fun NavGameHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = displayFormRoute) {
        displayFormScreen(onComplete = {
            navController.navigateToDisplayResult(it)
        })
        displayResultScreen()
    }
}
