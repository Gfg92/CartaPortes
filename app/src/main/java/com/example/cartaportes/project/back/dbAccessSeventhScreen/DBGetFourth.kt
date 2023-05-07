package com.example.cartaportes.project.back.dbAccessSeventhScreen

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getLicense(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteLicense")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val license = dataSnapshot.child("licensePlate").getValue(String::class.java) ?: ""
            callback(license)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}
fun getTrailerLicense(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteTrailerLicense")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val license = dataSnapshot.child("trailerLicense").getValue(String::class.java) ?: ""
            callback(license)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}
fun getDriverName(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteDriverName")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val license = dataSnapshot.child("driverName").getValue(String::class.java) ?: ""
            callback(license)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

