package com.example.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.SearchFlow -> navController.navigate(MainNavGraphDirections.actionGlobalSearchFlow())
    }
}