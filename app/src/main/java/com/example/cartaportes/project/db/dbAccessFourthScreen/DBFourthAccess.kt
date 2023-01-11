package com.example.cartaportes.project.db.dbAccessFourthScreen

import android.graphics.Bitmap
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.cartaportes.project.db.dbAccessSeventhScreen.getImage
import com.google.firebase.storage.StorageReference


fun setBitmapToFirebase(bitmap: Bitmap) {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val data = byteArrayOutputStream.toByteArray()

    // Create a storage reference from our app
    val storageRef =
        FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}.jpg")
    val uploadTask = storageRef.putBytes(data)

    uploadTask.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // Get the download URL
            storageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                // Save the download URL to the database
                val databaseRef = FirebaseDatabase.getInstance().reference.child("images")
                databaseRef.push().setValue(downloadUrl.toString())
            }
        } else {
            // Handle unsuccessful uploads
            // ...
        }
    }
}


fun getImageFromFirebase(directory: String): Bitmap? {
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.getReference(directory)
    var bitmap: Bitmap? = null
    val ONE_MEGABYTE: Long = 1024 * 1024

    storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener { bytes ->
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }.addOnFailureListener { exception ->
        // Handle any errors
    }
    return bitmap
}





