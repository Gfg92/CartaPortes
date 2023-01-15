package com.example.cartaportes.project.db.dbAccessFifthScreen

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



fun setPayment(pay: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePayment")
    refWrite.child("payment").setValue(pay)
}

fun setPaymentWay(kind: String, price: String? = null) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteKindPayment")
    refWrite.child("kind").setValue(kind)
    val refWrite2 = refName.child("WritePrice")
    refWrite2.child("price").setValue(price)
}

fun setRefund(refund: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteRefund")
    refWrite.child("refund").setValue(refund)
}


