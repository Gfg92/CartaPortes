package com.example.cartaportes.project.screens.myScreen

import android.annotation.SuppressLint
import android.os.Environment
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartaportes.R
import com.example.cartaportes.project.db.dbAccessSeventhScreen.*
import com.example.cartaportes.project.screens.classes.Point
import kotlinx.coroutines.*
import java.io.File


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SeventhScreen() {
    // Operator
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
    // Consignee
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
    // Lugar de entrega
    val delivery = remember {
        mutableStateOf("")
    }
    getDelivery { delivery.value = it }
    // Lugar de recogida
    val picking = remember {
        mutableStateOf("")
    }
    getPicking { picking.value = it }
    // N de Bultos
    val packageNumber = remember {
        mutableStateOf("")
    }
    getPackage { packageNumber.value = it }
    // Tipo de embalage
    val packaging = remember {
        mutableStateOf("")
    }
    getPacking { packaging.value = it }
    // Tipo de naturaleza
    val lista = remember {
        getPackageKind()
    }
    // Peso total
    val weight = remember {
        mutableStateOf("")
    }
    getTotalWeight { weight.value = it }
    // Método de pago
    val payment = remember {
        mutableStateOf("")
    }
    getPayment { payment.value = it }
    // Forma de pago
    val kind = remember {
        mutableStateOf("")
    }
    getPaymentKind { kind.value = it }
    // Precio
    val price = remember {
        mutableStateOf("")
    }
    getPrice { price.value = it }
    // Reembolso
    val refund = remember {
        mutableStateOf("")
    }
    getRefund { refund.value = it }
    // Matrícula
    val license = remember {
        mutableStateOf("")
    }
    getLicense { license.value = it }
    // Fecha
    val date = remember {
        mutableStateOf("")
    }
    getDate { date.value = it }
    // Firma
    var points = remember { mutableStateOf(mutableStateListOf<Point>()) }
    getSign { points.value = it }
    // Imagen
    val imageBitmap = runBlocking { getImageBitmap() }

    // PDF
    val context = LocalContext.current
    requestForegroundPermission(context)

    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState(0))
        ) {
            Text(
                text = stringResource(id = R.string.transport_operator),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ), modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Nombre: ${name.value}\nDNI: ${dni.value}\nAddress: ${address.value}\nCountry: ${country.value}")

            Text(
                text = stringResource(id = R.string.consignee),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Nombre: ${name1.value}\nDNI: ${dni1.value}\nAddress: ${address1.value}")

            Text(
                text = stringResource(id = R.string.delivery),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Entrega: ${delivery.value}")

            Text(
                text = stringResource(id = R.string.picking),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Rercogida: ${picking.value}")

            Text(
                text = stringResource(id = R.string.number_packages),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Número de paquetes: ${packageNumber.value}")

            Text(
                text = stringResource(id = R.string.check_title),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Tipo: ${packaging.value}")


            Text(
                text = stringResource(id = R.string.check_nature_title),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            for (e in lista) {
                Text(text = e)
            }

            Text(
                text = stringResource(id = R.string.total_weight),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Peso: ${weight.value} kgs")

            Text(
                text = stringResource(id = R.string.method_of_payment),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Pagador: ${payment.value}")

            Text(
                text = stringResource(id = R.string.way_of_payment),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "${kind.value}")

            Text(
                text = stringResource(id = R.string.total_price),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Precio: ${price.value}€")

            Text(
                text = stringResource(id = R.string.reimbursement),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                )
            )
            Text(text = "${refund.value}")

            Text(
                text = stringResource(id = R.string.vehicle),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Matrícula: ${license.value}")

            Text(
                text = stringResource(id = R.string.date_of_issue),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "${date.value}")

            Text(
                text = stringResource(id = R.string.sign_in),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            Canvas(
                modifier = Modifier
                    .size(width = 400.dp, height = 200.dp)
            ) {
                var first = true
                var startx = 0f
                var starty = 0f
                for (dot in points.value) {
                    if (
                        dot.x > this.size.width ||
                        dot.y > this.size.height ||
                        dot.x < 0 ||
                        dot.y < 0
                    ) {
                        continue
                    }
                    if (dot.x == -1f && dot.y == -1f) {
                        first = true
                    } else
                        if (first) {
                            startx = dot.x
                            starty = dot.y
                            first = false
                        } else {
                            drawLine(
                                color = dot.color,
                                start = Offset(x = startx, y = starty),
                                end = Offset(x = dot.x, y = dot.y),
                                strokeWidth = 10f
                            )
                            startx = dot.x
                            starty = dot.y
                        }
                }
            }

            Text(
                text = stringResource(id = R.string.image_in),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            if (imageBitmap != null) {
                androidx.compose.foundation.Image(
                    bitmap = imageBitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.width(400.dp)
                )
            }

            Button(onClick = {
                // CREATE A .TXT
//                val downloadsDir =
//                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                val pdfFile = File(downloadsDir, "hola.txt")
//                pdfFile.delete()
//                pdfFile.writeText("${name.value}")
                // CREATE A .PDF
                generatePDF(context, getDirectory())

            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Generar PDF")
            }


        }
    }

}

private fun getDirectory(): File {
    val mediaDir =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) as File
    return mediaDir
}










