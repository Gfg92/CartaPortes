package com.example.cartaportes.project.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
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


@Composable
fun Login(navController: NavController) {
    Scaffold(
        backgroundColor = Color(155, 154, 255),
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(id = R.string.title_name),
                    fontFamily = FontFamily(Font(R.font.highspeed)),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(start = 16.dp)
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
                modifier = Modifier.padding(bottom = 50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.camion),
                contentDescription = stringResource(id = R.string.content_description_truck),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(350.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .border(border = BorderStroke(2.dp, Color.Black))
            )
            Button(onClick = {
                navController.navigate("firstScreen")
            }, modifier = Modifier.padding(top = 50.dp)) {
                Text(
                    text = stringResource(id = R.string.text_button),
                    fontFamily = FontFamily(Font(R.font.highspeed)),
                    fontSize = 20.sp
                )

            }
        }
    }
}
