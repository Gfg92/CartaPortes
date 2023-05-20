package com.example.cartaportes.project.front.myScreen


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
import com.example.cartaportes.project.back.dbAccessSecondScreen.*
import com.example.cartaportes.project.back.dbAccessThirdScreen.setNatureKind
import com.example.cartaportes.project.back.dbAccessThirdScreen.setTotalWeight


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AboutMerchandise(navigate: NavController) {

    //Number of packages
    var numberPackages by remember {
        mutableStateOf("")
    }

    //Weight packages
    var weightPackages by remember {
        mutableStateOf("")
    }

    // CheckBox
    var picking = ""
    val checkedPrimary = remember { mutableStateOf(false) }
    val checkedSecondary = remember { mutableStateOf(false) }
    val checkedTertiary = remember { mutableStateOf(false) }
    if (checkedPrimary.value == true) {
        picking = stringResource(id = R.string.check_primary)
        checkedSecondary.value = false
        checkedTertiary.value = false
    }
    if (checkedSecondary.value == true) {
        picking = stringResource(id = R.string.check_secondary)
        checkedPrimary.value = false
        checkedTertiary.value = false
    }
    if (checkedTertiary.value == true) {
        picking = stringResource(id = R.string.check_tertiary)
        checkedPrimary.value = false
        checkedSecondary.value = false
    }

    // CheckBox
    val kindList = mutableListOf<String>()
    val checkedPerishable = remember { mutableStateOf(false) }
    val checkedNotPerishable = remember { mutableStateOf(false) }
    val checkedFragile = remember { mutableStateOf(false) }
    val checkedDangerous = remember { mutableStateOf(false) }
    val checkedDimensional = remember { mutableStateOf(false) }
    if (checkedPerishable.value == true) {
        kindList.add(stringResource(id = R.string.check_perishable))
    }
    if (checkedNotPerishable.value == true) {
        kindList.add(stringResource(id = R.string.check_not_perishable))
    }
    if (checkedFragile.value == true) {
        kindList.add(stringResource(id = R.string.check_fragile))
    }
    if (checkedDangerous.value == true) {
        kindList.add(stringResource(id = R.string.check_dangerous))
    }
    if (checkedDimensional.value == true) {
        kindList.add(stringResource(id = R.string.check_dimensional))
    }

    // FAB
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
        floatingActionButton = {
            Row() {
                FloatingActionButton(
                    onClick = {
                        navigate.navigate("personalDataScreen")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.fab_back),
                    )
                }
                FloatingActionButton(onClick = {
                    if ( numberPackages == "" || weightPackages == "") {
                        Toast.makeText(
                            context,
                            R.string.toast_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        setPackageNumber(numberPackages)
                        setTotalWeight(weightPackages)
                        setPacking(picking)
                        setNatureKind(kindList)
                        navigate.navigate("driverScreen")
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
                modifier = Modifier.padding(top = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 5.dp))


            // Weight Packages
            Text(
                text = stringResource(id = R.string.total_weight),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            OutlinedTextField(
                value = weightPackages,
                onValueChange = { weightPackages = it },
                label = { Text(stringResource(id = R.string.amount)) },
                textStyle = TextStyle(textAlign = TextAlign.End),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Text(
                text = "Peso: $weightPackages kgs",
                modifier = Modifier.padding(top = 16.dp)
            )

            Divider(modifier = Modifier.padding(top = 5.dp))

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

            // CheckBox
            Text(
                text = stringResource(id = R.string.check_nature_title),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedPerishable.value,
                    onCheckedChange = { checkedPerishable.value = it })
                Text(text = stringResource(id = R.string.check_perishable))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedNotPerishable.value,
                    onCheckedChange = { checkedNotPerishable.value = it })
                Text(text = stringResource(id = R.string.check_not_perishable))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedFragile.value,
                    onCheckedChange = { checkedFragile.value = it })
                Text(text = stringResource(id = R.string.check_fragile))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedDangerous.value,
                    onCheckedChange = { checkedDangerous.value = it })
                Text(text = stringResource(id = R.string.check_dangerous))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedDimensional.value,
                    onCheckedChange = { checkedDimensional.value = it })
                Text(text = stringResource(id = R.string.check_dimensional))
            }


        }
    }

}


