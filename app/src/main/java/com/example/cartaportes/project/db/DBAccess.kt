package com.example.cartaportes.project.db

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DBAccess() {

    fun getUserList(): MutableList<String> {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("Usuarios")

        val userList = mutableListOf<String>()

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children.map { it.getValue(String::class.java) }
                // Do something with the list of users
                for (e in users) {
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

    fun getCifList(): MutableList<String> {
        val database = FirebaseDatabase.getInstance()
        val cifRef = database.getReference("CIF")

        val cifList = mutableListOf<String>()

        cifRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cifs = snapshot.children.map { it.getValue(String::class.java) }
                // Do something with the list of users
                for (e in cifs) {
                    cifList.add(e.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
                Log.e("FirebaseDatabase", "Error reading data", error.toException())
            }
        })
        return cifList
    }

    fun getResidenceList(): MutableList<String> {
        val database = FirebaseDatabase.getInstance()
        val residenceRef = database.getReference("Domicilio")

        val residenceList = mutableListOf<String>()

        residenceRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val residences = snapshot.children.map { it.getValue(String::class.java) }
                // Do something with the list of users
                for (e in residences) {
                    residenceList.add(e.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
                Log.e("FirebaseDatabase", "Error reading data", error.toException())
            }
        })
        return residenceList
    }

    fun autoComplete(): String {
        var word = ""
        for (i in 0 until getUserList().size) {
            if (getUserList().get(i) == getUserList().get(i) && getUserList().get(i) == getResidenceList().get(
                    i
                )
            ) {
                word = getCifList().get(i) + getResidenceList().get(i)
            }
        }
        return word
    }
}















