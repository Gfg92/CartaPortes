package com.example.cartaportes.project.db.dbAccessSeventhScreen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cartaportes.R
import com.example.cartaportes.project.screens.classes.Point
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

private fun foregroundPermissionApproved(context: Context): Boolean {
    val writePermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val readPermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.READ_EXTERNAL_STORAGE
    )

    return writePermissionFlag && readPermissionFlag
}
fun requestForegroundPermission(context: Context) {
    val provideRationale = foregroundPermissionApproved(context = context)
    if (provideRationale) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }
}
fun generatePDF(
    context: Context,
    directory: File,
    name: String,
    dni: String,
    address: String,
    country: String,
    name1: String,
    dni1: String,
    address1: String,

    points: SnapshotStateList<Point>
) {
    val pageHeight = 1120
    val pageWidth = 792
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val title = Paint()
    val myPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val myPage = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas
    val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.camion))
    val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 30, 30, false)
    canvas.drawBitmap(scaleBitmap!!, 40f, 40f, paint)

    //Header
    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    title.textSize = 40f
    title.textAlign = Paint.Align.CENTER
    title.color = ContextCompat.getColor(context, R.color.black)
    canvas.drawText("CARTA DE PORTE", 396f, 100f, title)

    //Title
    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    title.textSize = 20f
    title.textAlign = Paint.Align.LEFT
    title.color = ContextCompat.getColor(context, R.color.black)
    canvas.drawText("Operador de transporte", 150f, 200f, title)
    canvas.drawText("Consignatario", 450f, 200f, title)
    canvas.drawText("Firma", 450f, 900f, title)
    //Text
    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    title.color = ContextCompat.getColor(context, R.color.black)
    title.textSize = 15f
    title.textAlign = Paint.Align.LEFT
    canvas.drawText("Nombre: $name", 150f, 220f, title)
    canvas.drawText("DNI: $dni", 150f, 240f, title)
    canvas.drawText("Dirección: $address", 150f, 260f, title)
    canvas.drawText("País: $country", 150f, 280f, title)
    canvas.drawText("Nombre: $name1", 450f, 220f, title)
    canvas.drawText("DNI: $dni1", 450f, 240f, title)
    canvas.drawText("Dirección: $address1", 450f, 260f, title)
    canvas.drawLine(150f, 300f, 650f, 301f, paint)


    //Sign
    var first = true
    var startx = 0f
    var starty = 0f
    //Move points
    val xOffset = 400f
    val yOffset = 900f
    for (dot in points) {
        // Sing size
        val dotx = dot.x / 4
        val doty = dot.y / 4
        if (
            dotx > canvas.width ||
            doty > canvas.height ||
            dotx < 0 ||
            doty < 0
        ) {
            continue
        }
        if (dot.x == -1f && dot.y == -1f) {
            first = true
        } else
            if (first) {
                startx = dotx
                starty = doty
                first = false
            } else {
                canvas.drawLine(
                    startx + xOffset,
                    starty + yOffset,
                    dotx + xOffset,
                    doty + yOffset,
                    paint
                )
                startx = dotx
                starty = doty
            }
    }



    pdfDocument.finishPage(myPage)
    val file = File(directory, "cartaPortes.pdf")

    try {
        pdfDocument.writeTo(FileOutputStream(file))
        Toast.makeText(context, "PDF file generated successfully", Toast.LENGTH_SHORT).show()
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
    pdfDocument.close()
}

fun getDirectory(): File {
    val mediaDir =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) as File
    return mediaDir
}

private fun drawableToBitmap(drawable: Drawable): Bitmap? {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}



