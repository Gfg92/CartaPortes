package com.example.cartaportes.project.db

import com.google.firebase.database.*

class DBAccess() {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Usuarios")

    fun getUserList(): MutableList<String> {
        val userList = mutableListOf<String>()

       usersRef.addChildEventListener(object : ChildEventListener {
           override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
               dataSnapshot.child("Nombre").getValue(String::class.java)?.let { userList.add(it) }
           }
           override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
           }
           override fun onChildRemoved(dataSnapshot: DataSnapshot) {
           }
           override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
           }
           override fun onCancelled(databaseError: DatabaseError) {
           }
       })
        return userList
    }

    fun getResidenceDni(): MutableList<String> {
        val list = mutableListOf<String>()
        usersRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
               list.add(dataSnapshot.child("DNI").getValue(String::class.java) + dataSnapshot.child("Domicilio").getValue(String::class.java))
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        return list
    }



}















