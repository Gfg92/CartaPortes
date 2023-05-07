package com.example.cartaportes.project.back.dbAccessSeventhScreen


import com.google.firebase.database.*


fun getPackageKind(): MutableList<String> {
    val list = mutableListOf<String>()
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePackageKind")
    ref.addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            var packcageKind = dataSnapshot.getValue(String::class.java)!!
            list.add(packcageKind)
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

fun getTotalWeight(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteWeight")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val weight = dataSnapshot.child("totalWeight").getValue(String::class.java) ?: ""
            callback(weight)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors here
        }
    })
}