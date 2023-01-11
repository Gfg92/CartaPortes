package com.example.cartaportes.project.screens.myScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState(0))
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


            Text(
                text = stringResource(id = R.string.check_nature_title),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            val lista = remember {
                getPackageKind()
            }
            for (e in lista) {
                Text(text = e)
            }


            val weight = remember {
                mutableStateOf("")
            }
            getTotalWeight { weight.value = it }
            Text(
                text = stringResource(id = R.string.total_weight),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Peso: ${weight.value} kgs")


            val payment = remember {
                mutableStateOf("")
            }
            getPayment { payment.value = it }
            Text(
                text = stringResource(id = R.string.method_of_payment),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Pagador: ${payment.value}")

            val kind = remember {
                mutableStateOf("")
            }
            getPaymentKind { kind.value = it }

            Text(
                text = stringResource(id = R.string.way_of_payment),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "${kind.value}")


            val price = remember {
                mutableStateOf("")
            }
            getPrice { price.value = it }
            Text(
                text = stringResource(id = R.string.total_price),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Precio: ${price.value}€")


            val refund = remember {
                mutableStateOf("")
            }
            getRefund { refund.value = it }
            Text(
                text = stringResource(id = R.string.reimbursement),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                )
            )
            Text(text = "${refund.value}")

            val license = remember {
                mutableStateOf("")
            }
            getLicense { license.value = it }
            Text(
                text = stringResource(id = R.string.vehicle),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "Matrícula: ${license.value}")


            val date = remember {
                mutableStateOf("")
            }
            getDate { date.value = it }
            Text(
                text = stringResource(id = R.string.date_of_issue),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "${date.value}")


//            var imageUri by remember {
//                mutableStateOf<Uri?>(null)
//            }
//            val context = LocalContext.current
//            val bitmap = remember {
//                mutableStateOf<Bitmap?>(null)
//            }
//            imageUri = getImageFromFirebase()
//            imageUri?.let {
//                if (Build.VERSION.SDK_INT < 28) {
//                    bitmap.value = MediaStore.Images
//                        .Media.getBitmap(context.contentResolver, it)
//
//                } else {
//                    val source = ImageDecoder
//                        .createSource(context.contentResolver, it)
//                    bitmap.value = ImageDecoder.decodeBitmap(source)
//                }
//                bitmap.value?.let { btm ->
//                    Image(
//                        bitmap = btm.asImageBitmap(),
//                        contentDescription = null,
//                        modifier = Modifier.size(400.dp)
//                    )
//                }
//            }


        }
    }
}





