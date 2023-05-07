package com.example.cartaportes.project.back.dbAccessFourthScreen

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getVehicleList(): MutableList<String> {
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
fun getTrailerList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Trailer")

    val trailerList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (vehicle in dataSnapshot.children) {
                trailerList.add(vehicle.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })
    return trailerList
}

fun getDriverList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("DriverName")

    val trailerList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (vehicle in dataSnapshot.children) {
                trailerList.add(vehicle.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })
    return trailerList
}

fun setLicensePlate(license: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteLicense")
    refWrite.child("licensePlate").setValue(license)
}
fun setTrailerLicense(trailerLicense: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteTrailerLicense")
    refWrite.child("trailerLicense").setValue(trailerLicense)
}
fun setDriverName(name: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteDriverName")
    refWrite.child("driverName").setValue(name)
}