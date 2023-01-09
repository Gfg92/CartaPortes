package com.example.cartaportes.project.db.AccessFifthScreen

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getPayerList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Payer")

    val payerList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (payer in dataSnapshot.children) {
                payerList.add(payer.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })

    return payerList
}

fun getVehicleList(): MutableList<String>{
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Vehicle")

    val vehicleList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (vehicle in dataSnapshot.children) {
                vehicleList.add(vehicle.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })

    return vehicleList
}