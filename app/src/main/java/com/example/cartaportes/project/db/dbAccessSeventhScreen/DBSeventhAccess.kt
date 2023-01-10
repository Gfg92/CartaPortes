package com.example.cartaportes.project.db.dbAccessSeventhScreen

import com.google.firebase.database.*

fun getOperatorData(): String {
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("Operators/WriteNameOperator")
    var nombre = ""


    return nombre
}