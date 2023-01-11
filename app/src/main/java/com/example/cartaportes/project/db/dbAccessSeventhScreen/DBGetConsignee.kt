package com.example.cartaportes.project.db.dbAccessSeventhScreen

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getConsigneeName(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameConsignee")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val name = dataSnapshot.child("name").getValue(String::class.java) ?: ""
            callback(name)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}
fun getConsigneeDni(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameConsignee")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val dni = dataSnapshot.child("dni").getValue(String::class.java) ?: ""
            callback(dni)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}
fun getConsigneeAddress(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameConsignee")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val address = dataSnapshot.child("address").getValue(String::class.java) ?: ""
            callback(address)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}