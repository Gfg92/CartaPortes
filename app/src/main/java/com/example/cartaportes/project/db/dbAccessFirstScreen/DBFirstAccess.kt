package com.example.cartaportes.project.db.dbAccessFirstScreen

import com.example.cartaportes.project.db.dbAccessFirstScreen.classes.Consignee
import com.example.cartaportes.project.db.dbAccessFirstScreen.classes.User
import com.google.firebase.database.*


fun getNameList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Users")
    val userList = mutableListOf<String>()

    usersRef.addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            dataSnapshot.child("name").getValue(String::class.java)?.let { userList.add(it) }
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

    return userList
}


fun getUserList(): MutableList<User> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Users")
    val usersList = mutableListOf<User>()

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (childSnapshot in dataSnapshot.children) {
                val name = childSnapshot.child("name").getValue(String::class.java)
                val dni = childSnapshot.child("dni").getValue(String::class.java)
                val address = childSnapshot.child("address").getValue(String::class.java)
                val country = childSnapshot.child("country").getValue(String::class.java)
                if (name != null && dni != null && address != null && country != null) {
                    usersList.add(User(name, dni, address, country))
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return usersList
}




fun getNameConsigList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Consignee")
    val userList = mutableListOf<String>()

    usersRef.addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            dataSnapshot.child("name").getValue(String::class.java)?.let { userList.add(it) }
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

    return userList
}


fun getConsigList(): MutableList<Consignee> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Consignee")
    val consigList = mutableListOf<Consignee>()

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (childSnapshot in dataSnapshot.children) {
                val name = childSnapshot.child("name").getValue(String::class.java)
                val dni = childSnapshot.child("dni").getValue(String::class.java)
                val address = childSnapshot.child("address").getValue(String::class.java)
                if (name != null && dni != null && address != null) {
                    consigList.add(Consignee(name, dni, address))
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return consigList
}

fun setOperator(name: String, dni: String, address: String, country: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteNameOperator")
    refWrite.child("name").setValue(name)
    refWrite.child("dni").setValue(dni)
    refWrite.child("address").setValue(address)
    refWrite.child("country").setValue(country)
}

fun setConsignee(name: String, dni: String, address: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteNameConsignee")
    refWrite.child("name").setValue(name)
    refWrite.child("dni").setValue(dni)
    refWrite.child("address").setValue(address)
}




















