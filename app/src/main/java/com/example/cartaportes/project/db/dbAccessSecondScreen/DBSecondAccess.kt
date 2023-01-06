package com.example.cartaportes.project.db.dbAccessSecondScreen

import com.google.firebase.database.*

fun getDeliveryPlaces(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Delivery")

    val deliveryCompanies = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (deliveryCompany in dataSnapshot.children) {
                deliveryCompanies.add(deliveryCompany.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })

    return deliveryCompanies
}

fun getPickingPlaces(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Picking")

    val pickingCompanies = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (deliveryCompany in dataSnapshot.children) {
                pickingCompanies.add(deliveryCompany.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })
    return  pickingCompanies
}