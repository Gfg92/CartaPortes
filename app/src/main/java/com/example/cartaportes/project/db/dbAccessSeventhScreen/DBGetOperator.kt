package com.example.cartaportes.project.db.dbAccessSeventhScreen

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cartaportes.project.screens.classes.Point
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// OPERATOR
fun getOperatorName(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val name = dataSnapshot.child("name").getValue(String::class.java) ?: ""
            callback(name)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}
//OTRA FORMA DE OBTENER EL NOMBRE
//suspend fun getOperatorData(): String {
//    var name = ""
//    withContext(Dispatchers.IO) {
//        val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
//        val dataSnapshot = ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                name = dataSnapshot.child("name").getValue(String::class.java) ?: ""
//            }
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Handle errors here
//            }
//        })
//    }
//    return name
//}

fun getOperatorDni(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val dni = dataSnapshot.child("dni").getValue(String::class.java) ?: ""
            callback(dni)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

fun getOperatorAddress(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val address = dataSnapshot.child("address").getValue(String::class.java) ?: ""
            callback(address)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}
fun getOperatorCountry(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val country = dataSnapshot.child("country").getValue(String::class.java) ?: ""
            callback(country)
        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}














