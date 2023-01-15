package com.example.cartaportes.project.db.dbAccessLoginScreen

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
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                navition.navigate("firstScreen")
                // update UI
            } else {
                // If sign in fails, display a message to the user.
                // update UI
                Toast.makeText(context, "Los datos introducidos no son correctos", Toast.LENGTH_SHORT).show()
            }
        }
}