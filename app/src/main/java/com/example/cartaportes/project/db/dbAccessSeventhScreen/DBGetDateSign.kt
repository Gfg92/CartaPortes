package com.example.cartaportes.project.db.dbAccessSeventhScreen

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cartaportes.project.screens.classes.Point
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getDate(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteDate")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val date = dataSnapshot.child("date").getValue(String::class.java) ?: ""
            callback(date)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}



// SIGN
fun getSign(callback: (SnapshotStateList<Point>) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteSign")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val sign = SnapshotStateList<Point>()
            dataSnapshot.children.forEach {
                val point = it.getValue(Point::class.java)
                if (point != null) sign.add(point)
            }
            callback(sign)
        }
        override fun onCancelled(error: DatabaseError) {
            // Handle error
        }
    })
}