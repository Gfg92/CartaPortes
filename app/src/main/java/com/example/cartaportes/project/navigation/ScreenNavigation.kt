package com.example.cartaportes.project.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartaportes.project.screens.login.Login
import com.example.cartaportes.project.screens.login.Prueba
import com.example.cartaportes.project.screens.myScreen.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "prueba") {
        composable("prueba") { Prueba(navController) }
        composable("login") { Login(navController) }
        composable("firstScreen") { FirstScreen(navController) }
        composable("secondScreen") { SecondScreen(navController) }
        composable("thirdScreen") { ThirdScreen(navController) }
        composable("fourthScreen") { FourthScreen(navController) }
        composable("fifthScreen") { FifthScreen(navController) }
        composable("sixthScreen") { SixthScreen(navController) }
        composable("seventhScreen") { SeventhScreen() }
    }
}