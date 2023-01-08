package com.example.cartaportes.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartaportes.project.screens.login.Login
import com.example.cartaportes.project.screens.myScreen.FirstScreen
import com.example.cartaportes.project.screens.myScreen.FouthScreen
import com.example.cartaportes.project.screens.myScreen.SecondScreen
import com.example.cartaportes.project.screens.myScreen.ThirdScreen

@Composable
fun NavigationScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { Login(navController) }
        composable("firstScreen") { FirstScreen(navController) }
        composable("secondScreen") { SecondScreen(navController) }
        composable("thirdScreen") { ThirdScreen(navController) }
        composable("fourthScreen") { FouthScreen(navigate = navController) }
    }
}