package com.example.cartaportes.project.screens.myScreen


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import com.example.cartaportes.project.db.dbAccessSecondScreen.getDeliveryPlaces
import com.example.cartaportes.project.db.dbAccessSecondScreen.getPickingPlaces


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondScreen(navigate: NavController) {

    // Delivery
    val deliveryList = getDeliveryPlaces()
    var selectedPlaceDelivery by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val namePlaceDel = selectedPlaceDelivery


    // Picking
    val pickingList = getPickingPlaces()
    var selectedPlacePicking by remember {
        mutableStateOf("")
    }
    var expanded1 by remember {
        mutableStateOf(false)
    }
    val namePlacePick = selectedPlacePicking

    //Number of packages
    var numberPackages by remember {
        mutableStateOf("")
    }

    // CheckBox
    val checkedPrimary = remember { mutableStateOf(false) }
    val checkedSecondary = remember { mutableStateOf(false) }
    val checkedTertiary = remember { mutableStateOf(false) }
    if (checkedPrimary.value == true) {
        checkedSecondary.value = false
        checkedTertiary.value = false
    }
    if (checkedSecondary.value == true) {
        checkedPrimary.value = false
        checkedTertiary.value = false
    }
    if (checkedTertiary.value == true) {
        checkedPrimary.value = false
        checkedSecondary.value = false
    }

    // FAB
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Color(155, 154, 255),
        floatingActionButton = {
            Row() {
                FloatingActionButton(
                    onClick = {
                        navigate.navigate("firstScreen")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.fab_back),
                    )
                }
                FloatingActionButton(onClick = {
                    if (selectedPlaceDelivery == "" || selectedPlacePicking == "" || numberPackages == "") {
                        Toast.makeText(
                            context,
                            R.string.toast_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        navigate.navigate("thirdScreen")
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
                text = stringResource(id = R.string.delivery),
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
                    value = selectedPlaceDelivery,
                    onValueChange = { selectedPlaceDelivery = it },
                    label = { Text(text = stringResource(id = R.string.label_delivery)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    deliveryList.filter { it.contains(selectedPlaceDelivery, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedPlaceDelivery = selectionOption
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
                text = "Lugar de entrega: $namePlaceDel",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))

            Text(
                text = stringResource(id = R.string.picking),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded1,
                onExpandedChange = {
                    expanded1 = !expanded1
                }
            ) {
                OutlinedTextField(
                    value = selectedPlacePicking,
                    onValueChange = { selectedPlacePicking = it },
                    label = { Text(text = stringResource(id = R.string.label_picking)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded1
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    pickingList.filter { it.contains(selectedPlacePicking, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded1,
                        onDismissRequest = { expanded1 = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedPlacePicking = selectionOption
                                    expanded1 = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }

            }

            Text(
                text = "Lugar de recogida: $namePlacePick",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))

            // Number of packages
            Text(
                text = stringResource(id = R.string.number_packages),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            OutlinedTextField(
                value = numberPackages,
                onValueChange = { numberPackages = it },
                label = { Text(stringResource(id = R.string.amount)) },
                textStyle = TextStyle(textAlign = TextAlign.End),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Text(
                text = "Número de bultos: $numberPackages",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))


            // CheckBox
            Text(
                text = stringResource(id = R.string.check_title),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedPrimary.value,
                    onCheckedChange = { checkedPrimary.value = it })
                Text(text = stringResource(id = R.string.check_primary))
                Checkbox(
                    checked = checkedSecondary.value,
                    onCheckedChange = { checkedSecondary.value = it })
                Text(text = stringResource(id = R.string.check_secondary))
                Checkbox(
                    checked = checkedTertiary.value,
                    onCheckedChange = { checkedTertiary.value = it })
                Text(text = stringResource(id = R.string.check_tertiary))
            }


        }
    }

}


