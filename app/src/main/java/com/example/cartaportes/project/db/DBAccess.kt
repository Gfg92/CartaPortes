package com.example.cartaportes.project.db

import com.google.firebase.database.*


fun getUserList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Usuarios")
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


fun getNameAddressDniList(): MutableList<User> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Usuarios")
    val usersList = mutableListOf<User>()

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (childSnapshot in dataSnapshot.children) {
                val name = childSnapshot.child("name").getValue(String::class.java)
                val dni = childSnapshot.child("dni").getValue(String::class.java)
                val address = childSnapshot.child("address").getValue(String::class.java)
                if (name != null && dni != null && address != null) {
                    usersList.add(User(name, dni, address))
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return usersList
}



















