package com.example.cartaportes.project.screens.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartaportes.R
import com.example.cartaportes.project.db.getConsigList
import com.example.cartaportes.project.db.getNameConsigList
import com.example.cartaportes.project.db.getUserList
import com.example.cartaportes.project.db.getNameList


@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectedUserUI() {

    // Operador de transporte

    val userList = getNameList()

    var selectedName by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.operador_transporte),
            fontSize = 15.sp,
            fontFamily = FontFamily(
                Font(R.font.highspeed)
            )
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedName,
                onValueChange = { selectedName = it },
                label = { Text(text = stringResource(id = R.string.label_usuario)) },
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
        Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))


        val name = selectedName

        val list = remember {
            getUserList()
        }
        var dni = ""
        var address = ""
        var country = ""
        for (e in list) {
            if (name == e.name) {
                dni = e.dni
                address = e.address
                country = e.country
            }
        }

        Text(text = "El nombre es: $name\nEl DNI es: $dni\nLa dirección es: $address\nEl país es: $country")

        // Consignatario

        val consigList = getNameConsigList()

        var selectedNameConsig by remember {
            mutableStateOf("")
        }

        var expanded1 by remember {
            mutableStateOf(false)
        }

        Text(
            text = stringResource(id = R.string.consignatario),
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
            TextField(
                value = selectedNameConsig,
                onValueChange = { selectedNameConsig = it },
                label = { Text(text = stringResource(id = R.string.label_consignatario)) },
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
        Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))


        val nameConsig = selectedNameConsig

        val list1 = remember {
            getConsigList()
        }
        var dni1 = ""
        var address1 = ""
        for (e in list1) {
            if (nameConsig == e.name) {
                dni1 = e.dni
                address1 = e.address
            }
        }

        Text(text = "El nombre es: $nameConsig\nEl DNI es: $dni1\nLa dirección es: $address1")


    }

}








