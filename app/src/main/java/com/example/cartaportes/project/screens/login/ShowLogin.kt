package com.example.cartaportes.project.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.eye_text_view_foreground)
    else
        painterResource(id = R.drawable.eye_text_view_oposite_foreground)

    // Button
    val context = LocalContext.current

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
        ) {
            Text(
                text = stringResource(id = R.string.title_page),
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.highspeed)),
                letterSpacing = 1.sp,
                modifier = Modifier.padding(top = 30.dp, bottom = 20.dp), color = Color.White
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
            OutlinedTextField(value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text(text = "Email") },
                label = {
                    Text(
                        text = "Email"
                    )
                })
            Text(
                text = "Contraseña",
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ), modifier = Modifier.padding(top = 16.dp)
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                placeholder = { Text(text = "Password") },
                label = { Text(text = "Password") },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )


            Button(onClick = {
                loginAccess(email.value, password.value, navController, context)
            }, modifier = Modifier.padding(top = 30.dp)) {
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
