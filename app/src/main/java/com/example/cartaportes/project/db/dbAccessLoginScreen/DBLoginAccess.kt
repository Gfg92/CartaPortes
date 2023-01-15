package com.example.cartaportes.project.db.dbAccessLoginScreen

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

fun loginAccess(email: String, password: String, navition: NavController) {
    val auth = FirebaseAuth.getInstance()
    val em = email
    val pass = password
    auth.signInWithEmailAndPassword(em, pass)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                navition.navigate("firstScreen")
                // update UI
            } else {
                // If sign in fails, display a message to the user.
                // update UI
            }
        }
}