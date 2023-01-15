package com.example.cartaportes.project.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cartaportes.R
import com.example.cartaportes.project.db.dbAccessLoginScreen.loginAccess


@Composable
fun Login(navController: NavController) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(id = R.string.title_name),
                    fontFamily = FontFamily(Font(R.font.highspeed)),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.White
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.title_page),
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.highspeed)),
                letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 50.dp), color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.camion),
                contentDescription = stringResource(id = R.string.content_description_truck),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .border(border = BorderStroke(2.dp, Color.Black))
            )
            Text(
                text = "Usuario",
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ), modifier = Modifier.padding(top = 16.dp)
            )
            TextField(value = email.value, onValueChange = { email.value = it })
            Text(
                text = "Contraseña",
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ), modifier = Modifier.padding(top = 16.dp)
            )
            TextField(value = password.value, onValueChange = { password.value = it })


            Button(onClick = {
                loginAccess(email.value, password.value, navController)
            }, modifier = Modifier.padding(top = 50.dp)) {
                Text(
                    text = stringResource(id = R.string.text_button),
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.highspeed)),
                    fontSize = 20.sp
                )

            }
        }
    }
}
