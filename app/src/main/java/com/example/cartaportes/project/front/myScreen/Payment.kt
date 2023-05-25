package com.example.cartaportes.project.front.myScreen

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cartaportes.R
import com.example.cartaportes.project.back.getters.getPayerList
import com.example.cartaportes.project.back.setters.setDate
import com.example.cartaportes.project.back.setters.setPayment
import com.example.cartaportes.project.back.setters.setPaymentWay
import com.example.cartaportes.project.back.setters.setRefund
import java.util.Date

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Payment(navigate: NavController) {


    // Payer
    val payerList = getPayerList()
    var selectedpayer by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val namePayment = selectedpayer


    // CheckBox
    var paidValue = ""
    val checkedPaid = remember { mutableStateOf(false) }
    val checkedDue = remember { mutableStateOf(false) }
    if (checkedPaid.value == true) {
        paidValue = stringResource(id = R.string.paid)
        checkedDue.value = false
    }
    if (checkedDue.value == true) {
        paidValue = stringResource(id = R.string.due)
        checkedPaid.value = false
    }

    // Price
    var price by remember {
        mutableStateOf("")
    }

    // CheckBox2
    var refund = ""
    val checkedYes = remember { mutableStateOf(false) }
    val checkedNo = remember { mutableStateOf(false) }
    if (checkedYes.value == true) {
        refund = stringResource(id = R.string.yes)
        checkedNo.value = false
    }
    if (checkedNo.value == true) {
        refund = stringResource(id = R.string.no)
        checkedYes.value == false
    }

    // Fetching the Local Context
    val mContext = LocalContext.current
    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()
    // Fetching current year, month and day
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }
    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    // FAB
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
        floatingActionButton = {
            Row() {
                FloatingActionButton(
                    onClick = {
                        navigate.navigate("driverScreen")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.fab_back),
                    )
                }
                FloatingActionButton(onClick = {
                    if (namePayment == "" || refund == "" || mDate.value == "") {
                        Toast.makeText(
                            context,
                            R.string.toast_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (checkedPaid.value == false && checkedDue.value == false) {
                        Toast.makeText(
                            context,
                            R.string.toast_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        setPayment(namePayment)
                        setPaymentWay(paidValue, price)
                        setRefund(refund)
                        setDate(mDate.value)
                        navigate.navigate("signatureScreen")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = stringResource(id = R.string.fab_next)
                    )
                }
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.method_of_payment),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    value = selectedpayer,
                    onValueChange = { selectedpayer = it },
                    label = { Text(text = stringResource(id = R.string.payer)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    payerList.filter { it.contains(selectedpayer, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedpayer = selectionOption
                                    expanded = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }

            }

            Text(
                text = "Pagador: $namePayment",
                modifier = Modifier.padding(top = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 5.dp))

            // CheckBox
            Text(
                text = stringResource(id = R.string.way_of_payment),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedPaid.value,
                    onCheckedChange = { checkedPaid.value = it })
                Text(text = stringResource(id = R.string.paid))

                Checkbox(
                    checked = checkedDue.value,
                    onCheckedChange = { checkedDue.value = it })
                Text(text = stringResource(id = R.string.due))
            }
            if (checkedDue.value == true) {
                Divider(modifier = Modifier.padding(top = 5.dp))
                Text(
                    text = stringResource(id = R.string.total_price),
                    fontSize = 15.sp,
                    fontFamily = FontFamily(
                        Font(R.font.highspeed)
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text(stringResource(id = R.string.price)) },
                    textStyle = TextStyle(textAlign = TextAlign.End),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Text(
                    text = "Precio: $price €",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Divider(modifier = Modifier.padding(top = 5.dp))

            // CheckBox2
            Text(
                text = stringResource(id = R.string.reimbursement),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedYes.value,
                    onCheckedChange = { checkedYes.value = it })
                Text(text = stringResource(id = R.string.yes))

                Checkbox(
                    checked = checkedNo.value,
                    onCheckedChange = { checkedNo.value = it })
                Text(text = stringResource(id = R.string.no))
            }

            Divider(modifier = Modifier.padding(top = 5.dp))

            Text(
                text = stringResource(id = R.string.date_of_issue),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(onClick = {
                mDatePickerDialog.show()
            }) {
                Text(text = stringResource(id = R.string.open_date_picker), color = Color.White)
            }
            Text(
                text = "Fecha seleccionada: ${mDate.value}",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }
    }
}