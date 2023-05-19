package com.example.cartaportes.project.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartaportes.project.front.login.Login
import com.example.cartaportes.project.front.myScreen.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationController(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "firstScreen") {
        composable("login") { Login(navController) }
        composable("firstScreen") { PersonalData(navController) }
        composable("secondScreen") { AboutMerchandise(navController) }
        composable("thirdScreen") { Driver(navController) }
        composable("fourthScreen") { Payment(navController) }
        composable("fifthScreen") { Signature(navController) }
        composable("sixthScreen") { SixthScreen(navController) }
        composable("seventhScreen") { SeventhScreen() }
    }
}