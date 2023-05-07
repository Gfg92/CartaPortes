package com.example.cartaportes.project.back.dbAccessThirdScreen

import com.google.firebase.database.FirebaseDatabase

fun setNatureKind(kind : MutableList<String>){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePackageKind")
    for (i in 0.. kind.size-1){
        refWrite.child(i.toString()).setValue(kind[i])
    }
}

fun setTotalWeight(weight: String){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteWeight")
    refWrite.child("totalWeight").setValue(weight)
}