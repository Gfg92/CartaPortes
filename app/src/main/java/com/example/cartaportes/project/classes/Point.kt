package com.example.cartaportes.project.classes

import androidx.compose.ui.graphics.Color

data class Point(val x: Float, val y: Float, val color: Color){
    constructor():this(0f,0f, Color(0, 0, 0))
}