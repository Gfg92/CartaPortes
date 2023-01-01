package com.example.cartaportes.db

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore


fun getUserList(): MutableList<String> {
    val userNames = mutableListOf<String>()

    val db = FirebaseFirestore.getInstance()
    val usersCollection = db.collection("usuarios")
    val usersTask = usersCollection.get()
    usersTask.addOnSuccessListener { documents ->
        for (document in documents) {
            val user = document.toObject(User::class.java)
            userNames.add(user.name)
        }
    }

    return userNames

}













