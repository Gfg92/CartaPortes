package com.example.cartaportes.project.db.dbAccessSeventhScreen


import com.example.cartaportes.project.db.classes.User
import com.google.firebase.database.*

fun getOperatorData(): MutableList<User> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Response/WriteNameOperator")
    val usersList = mutableListOf<User>()

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (childSnapshot in dataSnapshot.children) {
                val name = childSnapshot.child("name").getValue(String::class.java)
                val dni = childSnapshot.child("dni").getValue(String::class.java)
                val address = childSnapshot.child("address").getValue(String::class.java)
                val country = childSnapshot.child("country").getValue(String::class.java)

                usersList.add(User(name, dni, address, country))

            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return usersList
}

