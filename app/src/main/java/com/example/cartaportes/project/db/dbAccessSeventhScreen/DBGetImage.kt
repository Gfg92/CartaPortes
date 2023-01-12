package com.example.cartaportes.project.db.dbAccessSeventhScreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun getImageBitmap(): Bitmap? {
    val storage = FirebaseStorage.getInstance()
    val imageRef = storage.getReference("images/image.jpg")
    val url = imageRef.downloadUrl.await()
    val image = withContext(Dispatchers.IO) {
        URL(url.toString()).openStream().use {
            BitmapFactory.decodeStream(it)
        }
    }
    return image
}
