package com.example.cartaportes.project.screens.mainScreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartaportes.R
import com.example.cartaportes.project.db.DBAccess


@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectedUserUI() {
    val dbAccess = DBAccess()
    val userList = dbAccess.getUserList()

    var selectedName by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = stringResource(id = R.string.operador_transporte), fontSize = 20.sp)

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

        Text(text = "El nombre es: $selectedName\nEl DNI es: ")
    }
}






