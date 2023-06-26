package com.lv.fizzbuzzgame.ui.feature.displayform.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormRoute
import com.lv.fizzbuzzgame.ui.feature.displayform.NamesRoute

const val displayFormRoute = "display_form_route"

fun NavController.navigateToDisplayForm(navOptions: NavOptions? = null) {
    this.navigate(displayFormRoute, navOptions)
}

fun NavGraphBuilder.displayFormScreen(onComplete: (String) -> Unit) {
    composable(route = displayFormRoute) {
        NamesRoute()
    }
}
