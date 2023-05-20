package com.example.cartaportes.project.front.myScreen

import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
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
import com.example.cartaportes.project.back.classes.Point
import com.example.cartaportes.project.back.dbAccessFifthScreen.*
import com.example.cartaportes.project.back.dbAccessSixthScreen.setSign

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Signature(navigate: NavController) {



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
                    navigate.navigate("paymentScreen")
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
                    if (points.size == 0){
                        Toast.makeText(
                            context,
                            R.string.toast_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        setSign(points)
                        navigate.navigate("dataSummaryScreen")
                    }
                }) {
                    Text(text = stringResource(id = R.string.sign_send), color = Color.White)
                }
            }
        }
    }
}
