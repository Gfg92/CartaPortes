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
    val writePermissionFlag =
        PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            context, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    val readPermissionFlag =
        PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            context, Manifest.permission.READ_EXTERNAL_STORAGE
        )

    return writePermissionFlag && readPermissionFlag
}

fun requestForegroundPermission(context: Context) {
    val provideRationale = foregroundPermissionApproved(context = context)
    if (provideRationale) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
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
    delivery: String,
    picking: String,
    packageNumber: String,
    packaging: String,
    list: MutableList<String>,
    weight: String,
    payment: String,
    kind: String,
    price: String,
    refund: String,
    license: String,
    trailerLicense: String,
    driverName : String,
    date: String,
    points: SnapshotStateList<Point>
) {
    val pageHeight = 1120
    val pageWidth = 792
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val title = Paint()
    val subtitle = Paint()
    val text = Paint()
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
    subtitle.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    subtitle.textSize = 20f
    subtitle.textAlign = Paint.Align.LEFT
    subtitle.color = ContextCompat.getColor(context, R.color.black)
    canvas.drawText("Operador de transporte", 150f, 200f, subtitle)
    canvas.drawText("Consignatario", 450f, 200f, subtitle)
    canvas.drawText("Lugar de entrega", 150f, 350f, subtitle)
    canvas.drawText("Lugar de recogida", 450f, 350f, subtitle)
    canvas.drawText("Número de bultos", 150f, 400f, subtitle)
    canvas.drawText("Peso total", 450f, 400f, subtitle)
    canvas.drawText("Tipo de embalage", 150f, 450f, subtitle)
    canvas.drawText("Tipo de naturaleza", 450f, 450f, subtitle)
    canvas.drawText("Método de pago", 150f, 650f, subtitle)
    canvas.drawText("Forma de pago", 450f, 650f, subtitle)
    canvas.drawText("Reembolso", 150f, 700f, subtitle)
    canvas.drawText("Precio total", 450f, 700f, subtitle)
    canvas.drawText("Matrícula vehículo", 150f, 820f, subtitle)
    canvas.drawText("Matrícula remolque", 450f, 820f, subtitle)
    canvas.drawText("Firma operador", 150f, 950f, subtitle)
    canvas.drawText("Firma consignatario", 450f, 950f, subtitle)
    //Text
    text.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    text.color = ContextCompat.getColor(context, R.color.black)
    text.textSize = 15f
    text.textAlign = Paint.Align.LEFT
    canvas.drawText("Nombre: $name", 150f, 220f, text)
    canvas.drawText("DNI: $dni", 150f, 240f, text)
    canvas.drawText("Dirección: $address", 150f, 260f, text)
    canvas.drawText("País: $country", 150f, 280f, text)
    canvas.drawText("Nombre: $name1", 450f, 220f, text)
    canvas.drawText("DNI: $dni1", 450f, 240f, text)
    canvas.drawText("Dirección: $address1", 450f, 260f, text)
    canvas.drawLine(150f, 300f, 650f, 301f, paint)
    canvas.drawText("$delivery", 150f, 370f, text)
    canvas.drawText("$picking", 450f, 370f, text)
    canvas.drawText("$packageNumber bultos", 150f, 420f, text)
    canvas.drawText("$weight kgs", 450f, 420f, text)
    canvas.drawText("$packaging", 150f, 470f, text)
    var iteratorY = 470f
    for (e in list) {
        "${canvas.drawText(e, 450f, iteratorY, text)}\n"
        iteratorY += 20
    }
    canvas.drawLine(150f, 600f, 650f, 601f, paint)
    canvas.drawText("Pagador: $payment", 150f, 670f, text)
    canvas.drawText("$kind", 450f, 670f, text)
    canvas.drawText("$refund", 150f, 720f, text)
    if (price == ""){
        canvas.drawText("0 €", 450f, 720f, text)
    }else{
        canvas.drawText("$price €", 450f, 720f, text)
    }
    canvas.drawLine(150f, 770f, 650f, 771f, paint)
    canvas.drawText("$license", 150f, 840f, text)
    canvas.drawText("$trailerLicense", 450f, 840f, text)

    //Sign
    var first = true
    var startx = 0f
    var starty = 0f
    //Move points
    val xOffset = 150f
    val yOffset = 970f
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



