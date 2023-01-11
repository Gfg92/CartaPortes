package com.example.cartaportes.project.db.dbAccessSeventhScreen

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getPayment(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePayment")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val payment = dataSnapshot.child("payment").getValue(String::class.java) ?: ""
            callback(payment)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

fun getPaymentKind(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteKindPayment")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val kind = dataSnapshot.child("kind").getValue(String::class.java) ?: ""
            callback(kind)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}
fun getPrice(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePrice")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val price = dataSnapshot.child("price").getValue(String::class.java) ?: "0"
            callback(price)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

fun getRefund(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteRefund")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val refund = dataSnapshot.child("refund").getValue(String::class.java) ?: ""
            callback(refund)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}

fun getLicense(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteLicense")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val license = dataSnapshot.child("licensePlate").getValue(String::class.java) ?: ""
            callback(license)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}