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

    NavHost(navController = navController, startDestination = "signatureScreen") {
        composable("loginScreen") { Login(navController) }
        composable("personalDataScreen") { PersonalData(navController) }
        composable("aboutMerchandiseScreen") { AboutMerchandise(navController) }
        composable("driverScreen") { Driver(navController) }
        composable("paymentScreen") { Payment(navController) }
        composable("signatureScreen") { Signature(navController) }
        composable("dataSummaryScreen") { DataSummary() }
    }
}