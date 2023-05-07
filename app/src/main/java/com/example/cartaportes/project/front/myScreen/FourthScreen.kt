package com.example.cartaportes.project.front.myScreen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import com.example.cartaportes.project.back.dbAccessFourthScreen.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FourthScreen(navigate: NavController) {
    // Vehicle
    val vehicleList = getVehicleList()
    var selectedVehicle by remember {
        mutableStateOf("")
    }
    val nameVehicle = selectedVehicle
    var expandedVehicle by remember {
        mutableStateOf(false)
    }

    // Trailer
    val trailerList = getTrailerList()
    var selectedTrailer by remember {
        mutableStateOf("")
    }

    var expandedTrailer by remember {
        mutableStateOf(false)
    }

    // Driver name
    val driverList = getDriverList()
    var selectedDriver by remember {
        mutableStateOf("")
    }

    var expandedDriver by remember {
        mutableStateOf(false)
    }
    // FAB
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
        floatingActionButton = {
            Row() {
                FloatingActionButton(
                    onClick = {
                        navigate.navigate("thirdScreen")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.fab_back),
                    )
                }
                FloatingActionButton(onClick = {
                    if (selectedVehicle == "" || selectedTrailer == "" || selectedDriver == ""){
                        Toast.makeText(
                            context,
                            R.string.toast_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        setLicensePlate(selectedVehicle)
                        setTrailerLicense(selectedTrailer)
                        setDriverName(selectedDriver)
                        navigate.navigate("fifthScreen")
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
            // Vehicle
            Text(
                text = stringResource(id = R.string.vehicle),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            ExposedDropdownMenuBox(
                expanded = expandedVehicle,
                onExpandedChange = {
                    expandedVehicle = !expandedVehicle
                }
            ) {
                OutlinedTextField(
                    value = selectedVehicle,
                    onValueChange = { selectedVehicle = it },
                    label = { Text(text = stringResource(id = R.string.registration_number)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedVehicle
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    vehicleList.filter { it.contains(selectedVehicle, ignoreCase = true) }

                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expandedVehicle,
                        onDismissRequest = { expandedVehicle = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedVehicle = selectionOption
                                    expandedVehicle = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }

            }

            Text(
                text = "Matrícula: $nameVehicle",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))

            Text(
                text = stringResource(id = R.string.trailer_license),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expandedTrailer,
                onExpandedChange = {
                    expandedTrailer = !expandedTrailer
                }
            ) {
                OutlinedTextField(
                    value = selectedTrailer,
                    onValueChange = { selectedTrailer = it },
                    label = { Text(text = stringResource(id = R.string.trailer_selected)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedTrailer
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    trailerList.filter { it.contains(selectedTrailer, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expandedTrailer,
                        onDismissRequest = { expandedTrailer = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedTrailer = selectionOption
                                    expandedTrailer = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }

            }
            Text(
                text = "Matrícula: $selectedTrailer",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))



            Text(
                text = stringResource(id = R.string.driver),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expandedDriver,
                onExpandedChange = {
                    expandedDriver = !expandedDriver
                }
            ) {
                OutlinedTextField(
                    value = selectedDriver,
                    onValueChange = { selectedDriver = it },
                    label = { Text(text = stringResource(id = R.string.name_selected)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedDriver
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                // filter options based on text field value
                val filteringOptions =
                    driverList.filter { it.contains(selectedDriver, ignoreCase = true) }


                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expandedDriver,
                        onDismissRequest = { expandedDriver = false }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedDriver = selectionOption
                                    expandedDriver = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
            }
            Text(
                text = "Nombre: $selectedDriver",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }
    }
}