package com.example.cartaportes.project.screens.myScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cartaportes.R
import com.example.cartaportes.project.db.dbAccessSeventhScreen.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SeventhScreen(navigate: NavController) {

    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
        modifier = Modifier.verticalScroll(state = rememberScrollState(0))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val name = remember {
                mutableStateOf("")
            }
            getOperatorName { name.value = it }
            val dni = remember {
                mutableStateOf("")
            }
            getOperatorDni { dni.value = it }
            val address = remember {
                mutableStateOf("")
            }
            getOperatorAddress { address.value = it }
            val country = remember {
                mutableStateOf("")
            }
            getOperatorCountry { country.value = it }

            Text(
                text = stringResource(id = R.string.transport_operator),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ), modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Nombre: ${name.value}\nDNI: ${dni.value}\nAddress: ${address.value}\nCountry: ${country.value}")

            val name1 = remember {
                mutableStateOf("")
            }
            getConsigneeName { name1.value = it }
            val dni1 = remember {
                mutableStateOf("")
            }
            getConsigneeDni { dni1.value = it }
            val address1 = remember {
                mutableStateOf("")
            }
            getConsigneeAddress { address1.value = it }

            Text(
                text = stringResource(id = R.string.consignee),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Nombre: ${name1.value}\nDNI: ${dni1.value}\nAddress: ${address1.value}")

            val delivery = remember {
                mutableStateOf("")
            }
            getDelivery { delivery.value = it }

            Text(
                text = stringResource(id = R.string.delivery),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Entrega: ${delivery.value}")

            val picking = remember {
                mutableStateOf("")
            }
            getPicking { picking.value = it }
            Text(
                text = stringResource(id = R.string.picking),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Rercogida: ${picking.value}")

            val packageNumber = remember {
                mutableStateOf("")
            }
            getPackage { packageNumber.value = it }

            Text(
                text = stringResource(id = R.string.number_packages),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Número de paquetes: ${packageNumber.value}")


            val packaging = remember {
                mutableStateOf("")
            }
            getPacking { packaging.value = it }

            Text(
                text = stringResource(id = R.string.check_title),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Tipo: ${packaging.value}")

        }
    }
}





