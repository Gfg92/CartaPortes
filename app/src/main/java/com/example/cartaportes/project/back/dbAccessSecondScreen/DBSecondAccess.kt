package com.example.cartaportes.project.back.dbAccessSecondScreen

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

fun setDelivery(delivery: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteDelivery")
    refWrite.child("delivery").setValue(delivery)
}
fun setPicking(picking: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePicking")
    refWrite.child("picking").setValue(picking)
}
fun setPackageNumber(number:String){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePackage")
    refWrite.child("packageNumber").setValue(number)
}
fun setPacking(type: String){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePacking")
    refWrite.child("packaging").setValue(type)
}