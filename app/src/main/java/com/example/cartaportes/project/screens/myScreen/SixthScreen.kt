package com.example.cartaportes.project.screens.myScreen

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.view.MotionEvent
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cartaportes.R
import com.example.cartaportes.project.screens.classes.Punto
import java.util.Date

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SixthScreen(navigate: NavController) {

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

    // Sign
    val puntos = remember { mutableStateListOf<Punto>() }
    val colorSeleccionado by remember { mutableStateOf(Color.Black) }

    Scaffold(
        backgroundColor = Color(155, 154, 255),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigate.navigate("fifthScreen")
                },
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.fab_back),
                )
            }

        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
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

            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))

            Text(
                text = stringResource(id = R.string.sign),
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(R.font.highspeed)
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            Canvas(
                modifier = Modifier
                    .size(width = 400.dp, height = 200.dp)
                    .border(BorderStroke(1.dp, Color.Black))
                    .pointerInteropFilter {
                        when (it.actionMasked) {
                            MotionEvent.ACTION_UP -> {
                                puntos.add(Punto(-1f, -1f, colorSeleccionado))
                                true
                            }
                            MotionEvent.ACTION_MOVE -> {
                                puntos.add(Punto(it.x, it.y, colorSeleccionado))
                                true
                            }
                            MotionEvent.ACTION_DOWN -> {
                                puntos.add(Punto(it.x, it.y, colorSeleccionado))
                                true
                            }
                            else -> false
                        }
                    }) {
                var primera = true
                var iniciox = 0f
                var inicioy = 0f
                for (punto in puntos) {
                    if (punto.x == -1f && punto.y == -1f) {
                        primera = true
                    } else
                        if (primera) {
                            iniciox = punto.x
                            inicioy = punto.y
                            primera = false
                        } else {
                            drawLine(
                                color = punto.color,
                                start = Offset(x = iniciox, y = inicioy),
                                end = Offset(x = punto.x, y = punto.y),
                                strokeWidth = 10f
                            )
                            iniciox = punto.x
                            inicioy = punto.y
                        }
                }
            }
            Row() {
                Button(onClick = {

                }) {
                    Text(text = stringResource(id = R.string.sign_clear))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(onClick = {
                    navigate.navigate("seventhScreen")
                }) {
                    Text(text = stringResource(id = R.string.sign_send))
                }
            }
        }
    }
}


