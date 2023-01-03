package com.example.cartaportes.project.db

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore


fun getUserList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Usuarios")

    val userList = mutableListOf<String>()

    usersRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val users = snapshot.children.map { it.getValue(String::class.java) }
            // Do something with the list of users
            for (e in users){
                userList.add(e.toString())
            }
        }
        override fun onCancelled(error: DatabaseError) {
            // Handle the error
            Log.e("FirebaseDatabase", "Error reading data", error.toException())
        }
    })

    return userList

}













