package com.example.cartaportes.project.db.dbAccessSixthScreen

import com.google.firebase.database.FirebaseDatabase

fun setDate(date: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteDate")
    refWrite.child("date").setValue(date)
}