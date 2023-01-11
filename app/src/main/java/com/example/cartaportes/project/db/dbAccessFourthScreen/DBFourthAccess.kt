package com.example.cartaportes.project.db.dbAccessFourthScreen

import android.graphics.Bitmap
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import android.net.Uri



fun setBitmapToFirebase(bitmap: Bitmap) {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val data = byteArrayOutputStream.toByteArray()

    // Create a storage reference from our app
    val storageRef =
        FirebaseStorage.getInstance().reference.child("images/image.jpg")
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


//No funciona
//fun getImageFromFirebase(): Uri? {
//    val storage = FirebaseStorage.getInstance()
//    val storageRef = storage.getReference("images/*")
//    var imageUri: Uri? = null
//    storageRef.downloadUrl.addOnSuccessListener { uri ->
//        imageUri = uri
//    }.addOnFailureListener { exception ->
//        // Handle any errors
//    }
//    return imageUri
//}







