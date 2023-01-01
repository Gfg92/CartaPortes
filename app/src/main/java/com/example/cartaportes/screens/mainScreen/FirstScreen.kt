package com.example.cartaportes.screens.mainScreen

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.cartaportes.R
import com.example.cartaportes.db.getUserList


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectedUser() {
//    val userList = listOf("Juan", "Maria")
    val userList = getUserList()


    var selectedItem by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
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
            userList.filter { it.contains(selectedItem, ignoreCase = true) }

        if (filteringOptions.isNotEmpty()) {

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
}




