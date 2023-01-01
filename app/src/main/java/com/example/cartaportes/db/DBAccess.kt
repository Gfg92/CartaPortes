package com.example.cartaportes.db

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


fun getUserList(): List<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("users")
    val userList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            for (userSnapshot in dataSnapshot.children) {
                val user = userSnapshot.getValue(String::class.java)
                userList.add(user.toString())
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException())
        }
    })

    return userList
}











