package com.example.cartaportes.project.screens.myScreen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cartaportes.project.db.dbAccessSeventhScreen.getOperatorData

@Composable
fun SeventhScreen(navigate: NavController) {
    var nombre = ""
    for ( e in getOperatorData()){
        nombre = e.name!!
    }
    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = nombre)
        }
    }
}