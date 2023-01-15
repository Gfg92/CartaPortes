package com.example.cartaportes.project.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Prueba(navition: NavController) {

    val name = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column() {
        TextField(
            value = name.value,
            onValueChange = { name.value = it }
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it }
        )
        Button(onClick = { acceso(name.value, password.value, navition) }) {
            Text(text = "Login")
        }
    }



}

fun acceso(email: String, password: String, navition: NavController) {
    val auth = FirebaseAuth.getInstance()
    val em = email
    val pass = password
    auth.signInWithEmailAndPassword(em, pass)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                navition.navigate("login")
                // update UI
            } else {
                // If sign in fails, display a message to the user.
                // update UI
            }
        }
}