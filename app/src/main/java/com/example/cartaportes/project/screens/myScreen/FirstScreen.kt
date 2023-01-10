package com.example.cartaportes.project.screens.myScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cartaportes.R
import com.example.cartaportes.project.db.dbAccessFirstScreen.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FirstScreen(navigate: NavController) {
    // Driver operator

    val userList = getNameList()

    var selectedName by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val name = selectedName

    val list = remember {
        getUserList()
    }

    var dni = ""
    var address = ""
    var country = ""
    for (e in list) {
        if (name == e.name) {
            dni = e.dni!!
            address = e.address!!
            country = e.country!!
        }
    }

    // Consignee

    val consigList = getNameConsigList()

    var selectedNameConsig by remember {
        mutableStateOf("")
    }

    var expanded1 by remember {
        mutableStateOf(false)
    }

    val nameConsig = selectedNameConsig

    val list1 = remember {
        getConsigList()
    }
    var dni1 = ""
    var address1 = ""
    for (e in list1) {
        if (nameConsig == e.name) {
            dni1 = e.dni!!
            address1 = e.address!!
        }
    }

    // FAB
    val context = LocalContext.current


    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (selectedName == "" || selectedNameConsig == "") {
                    Toast.makeText(
                        context,
                        R.string.toast_error,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    setOperator(name, dni, address, country)
                    setConsignee(nameConsig, dni1, address1)
                    navigate.navigate("seventhScreen")
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
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    value = selectedName,
                    onValueChange = { selectedName = it },
                    label = { Text(text = stringResource(id = R.string.label_user)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    userList.filter { it.contains(selectedName, ignoreCase = true) }

                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedName = selectionOption
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
                text = "Nombre: $name\nDNI: $dni\nDirección: $address\nPaís: $country",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )


            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))

            Text(
                text = stringResource(id = R.string.consignee),
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
                    value = selectedNameConsig,
                    onValueChange = { selectedNameConsig = it },
                    label = { Text(text = stringResource(id = R.string.label_consignee)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded1
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    consigList.filter { it.contains(selectedNameConsig, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded1,
                        onDismissRequest = { expanded1 = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedNameConsig = selectionOption
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
                text = "Nombre: $nameConsig\nDNI: $dni1\nDirección: $address1",
                modifier = Modifier.padding(top = 16.dp)
            )

        }
    }
}












