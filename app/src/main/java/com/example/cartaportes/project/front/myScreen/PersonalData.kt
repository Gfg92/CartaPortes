package com.example.cartaportes.project.front.myScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cartaportes.R
import com.example.cartaportes.project.back.getters.getConsigList
import com.example.cartaportes.project.back.getters.getDeliveryPlaces
import com.example.cartaportes.project.back.getters.getNameConsigList
import com.example.cartaportes.project.back.getters.getNameList
import com.example.cartaportes.project.back.getters.getPickingPlaces
import com.example.cartaportes.project.back.getters.getUserList
import com.example.cartaportes.project.back.setters.setConsignee
import com.example.cartaportes.project.back.setters.setDelivery
import com.example.cartaportes.project.back.setters.setOperator
import com.example.cartaportes.project.back.setters.setPicking

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PersonalData(navigate: NavController) {
    // Driver operator

    val userList = getNameList()

    var selectedNameOperator by remember {
        mutableStateOf("")
    }

    var expandedDriverOperator by remember {
        mutableStateOf(false)
    }

    val name = selectedNameOperator

    val listOperator = remember {
        getUserList()
    }

    var dniOperator = ""
    var addressOperator = ""
    var countryOperator = ""
    for (e in listOperator) {
        if (name == e.name) {
            dniOperator = e.dni!!
            addressOperator = e.address!!
            countryOperator = e.country!!
        }
    }

    // Consignee

    val consigList = getNameConsigList()

    var selectedNameConsig by remember {
        mutableStateOf("")
    }

    var expandedConsignee by remember {
        mutableStateOf(false)
    }

    val nameConsig = selectedNameConsig

    val listConsignee = remember {
        getConsigList()
    }
    var dniConsignee = ""
    var addressConsignee = ""
    for (e in listConsignee) {
        if (nameConsig == e.name) {
            dniConsignee = e.dni!!
            addressConsignee = e.address!!
        }
    }

    // Delivery
    val deliveryList = getDeliveryPlaces()
    var selectedPlaceDelivery by remember {
        mutableStateOf("")
    }
    var expandedDelivery by remember {
        mutableStateOf(false)
    }
    val namePlaceDel = selectedPlaceDelivery


    // Picking
    val pickingList = getPickingPlaces()
    var selectedPlacePicking by remember {
        mutableStateOf("")
    }
    var expandedPicking by remember {
        mutableStateOf(false)
    }
    val namePlacePick = selectedPlacePicking

    // FAB
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current



    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (selectedNameOperator == "" || selectedNameConsig == "" || selectedPlaceDelivery == "" || selectedPlacePicking == "") {
                    Toast.makeText(
                        context,
                        R.string.toast_error,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    setOperator(name, dniOperator, addressOperator, countryOperator)
                    setConsignee(nameConsig, dniConsignee, addressConsignee)
                    setDelivery(namePlaceDel)
                    setPicking(namePlacePick)
                    navigate.navigate("aboutMerchandiseScreen")
                }
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = stringResource(id = R.string.fab_next)
                )
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.transport_operator),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ), modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expandedDriverOperator,
                onExpandedChange = {
                    expandedDriverOperator = !expandedDriverOperator
                }
            ) {
                OutlinedTextField(
                    value = selectedNameOperator,
                    onValueChange = { selectedNameOperator = it },
                    label = { Text(text = stringResource(id = R.string.label_user)) },
                    trailingIcon = {
                        keyboardController?.hide()
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedDriverOperator
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    userList.filter { it.contains(selectedNameOperator, ignoreCase = true) }

                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expandedDriverOperator,
                        onDismissRequest = { expandedDriverOperator = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedNameOperator = selectionOption
                                    expandedDriverOperator = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
            }

            Text(
                text = "Nombre: $name\nDNI: $dniOperator\nDirección: $addressOperator\nPaís: $countryOperator",
                modifier = Modifier.padding(top = 16.dp)
            )


            Divider(modifier = Modifier.padding(top = 5.dp))

            Text(
                text = stringResource(id = R.string.consignee),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expandedConsignee,
                onExpandedChange = {
                    expandedConsignee = !expandedConsignee
                }
            ) {
                OutlinedTextField(
                    value = selectedNameConsig,
                    onValueChange = { selectedNameConsig = it },
                    label = { Text(text = stringResource(id = R.string.label_consignee)) },
                    trailingIcon = {
                        keyboardController?.hide()
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedConsignee
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    consigList.filter { it.contains(selectedNameConsig, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expandedConsignee,
                        onDismissRequest = { expandedConsignee = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedNameConsig = selectionOption
                                    expandedConsignee = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }

            }

            Text(
                text = "Nombre: $nameConsig\nDNI: $dniConsignee\nDirección: $addressConsignee",
                modifier = Modifier.padding(top = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 5.dp))

            Text(
                text = stringResource(id = R.string.delivery),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expandedDelivery,
                onExpandedChange = {
                    expandedDelivery = !expandedDelivery
                }
            ) {
                OutlinedTextField(
                    value = selectedPlaceDelivery,
                    onValueChange = { selectedPlaceDelivery = it },
                    label = { Text(text = stringResource(id = R.string.label_delivery)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedDelivery
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    deliveryList.filter { it.contains(selectedPlaceDelivery, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expandedDelivery,
                        onDismissRequest = { expandedDelivery = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedPlaceDelivery = selectionOption
                                    expandedDelivery = false
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
                modifier = Modifier.padding(top = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 5.dp))

            Text(
                text = stringResource(id = R.string.picking),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expandedPicking,
                onExpandedChange = {
                    expandedPicking = !expandedPicking
                }
            ) {
                OutlinedTextField(
                    value = selectedPlacePicking,
                    onValueChange = { selectedPlacePicking = it },
                    label = { Text(text = stringResource(id = R.string.label_picking)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedPicking
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    pickingList.filter { it.contains(selectedPlacePicking, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expandedPicking,
                        onDismissRequest = { expandedPicking = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedPlacePicking = selectionOption
                                    expandedPicking = false
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
        }
    }
}












