package com.example.cartaportes.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartaportes.project.screens.login.Login
import com.example.cartaportes.project.screens.mainScreen.SelectedUserUI

@Composable
fun NavigationScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { Login(navController = navController) }
        composable("firstScreen") { SelectedUserUI() }
    }
}