package com.example.cartaportes.project.back.dbAccessSeventhScreen

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getDelivery(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteDelivery")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val delivery = dataSnapshot.child("delivery").getValue(String::class.java) ?: ""
            callback(delivery)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

fun getPicking(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePicking")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val picking = dataSnapshot.child("picking").getValue(String::class.java) ?: ""
            callback(picking)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

fun getPackage(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePackage")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val packageNumber = dataSnapshot.child("packageNumber").getValue(String::class.java) ?: ""
            callback(packageNumber)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

fun getPacking(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePacking")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val packaging = dataSnapshot.child("packaging").getValue(String::class.java) ?: ""
            callback(packaging)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}