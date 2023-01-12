package com.example.cartaportes.project.db.dbAccessSixthScreen

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cartaportes.project.screens.classes.Point
import com.google.firebase.database.FirebaseDatabase

fun setDate(date: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteDate")
    refWrite.child("date").setValue(date)
}
fun setSign(sign: SnapshotStateList<Point>){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteSign")
    refWrite.removeValue()
    for (i in 0.. sign.size-1){
        refWrite.child(i.toString()).setValue(sign[i])
    }
}