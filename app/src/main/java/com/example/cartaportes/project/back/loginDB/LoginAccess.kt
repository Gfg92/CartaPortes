package com.example.cartaportes.project.back.loginDB

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

fun loginAccess(email: String, password: String, navition: NavController, context: Context) {
    val auth = FirebaseAuth.getInstance()
    val email1 = email
    val password1 = password
    auth.signInWithEmailAndPassword(email1, password1)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                navition.navigate("personalDataScreen")
            } else {
                Toast.makeText(context, "Los datos introducidos no son correctos", Toast.LENGTH_SHORT).show()
            }
        }
}