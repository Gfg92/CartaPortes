package com.example.cartaportes.project.screens.myScreen

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.view.MotionEvent
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cartaportes.R
import com.example.cartaportes.project.db.dbAccessSixthScreen.setDate
import com.example.cartaportes.project.db.dbAccessSixthScreen.setSign
import com.example.cartaportes.project.screens.classes.Point
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
    val points = remember { mutableStateListOf<Point>() }
    val selectedColor by remember { mutableStateOf(Color.Black) }

    // FAB
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Color(167, 181, 216, 255),
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
                    .background(Color.White)
                    .pointerInteropFilter {
                        when (it.actionMasked) {
                            MotionEvent.ACTION_UP -> {
                                points.add(Point(-1f, -1f, selectedColor))
                                true
                            }
                            MotionEvent.ACTION_MOVE -> {
                                points.add(Point(it.x, it.y, selectedColor))
                                true
                            }
                            MotionEvent.ACTION_DOWN -> {
                                points.add(Point(it.x, it.y, selectedColor))
                                true
                            }
                            else -> false
                        }
                    }) {
                var first = true
                var startx = 0f
                var starty = 0f
                for (dot in points) {
                    if (
                        dot.x > this.size.width ||
                        dot.y > this.size.height ||
                        dot.x < 0 ||
                        dot.y < 0
                    ) {
                        continue
                    }
                    if (dot.x == -1f && dot.y == -1f) {
                        first = true
                    } else
                        if (first) {
                            startx = dot.x
                            starty = dot.y
                            first = false
                        } else {
                            drawLine(
                                color = dot.color,
                                start = Offset(x = startx, y = starty),
                                end = Offset(x = dot.x, y = dot.y),
                                strokeWidth = 10f
                            )
                            startx = dot.x
                            starty = dot.y
                        }
                }
            }
            Row() {
                Button(onClick = {
                    points.clear()
                }) {
                    Text(text = stringResource(id = R.string.sign_clear), color = Color.White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(onClick = {
                    if (mDate.value == ""|| points.size == 0){
                        Toast.makeText(
                            context,
                            R.string.toast_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        setDate(mDate.value)
                        setSign(points)
                        navigate.navigate("seventhScreen")
                    }
                }) {
                    Text(text = stringResource(id = R.string.sign_send), color = Color.White)
                }
            }
        }
    }
}


