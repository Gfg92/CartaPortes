package com.example.cartaportes.project.back.setters

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cartaportes.project.classes.Point
import com.google.firebase.database.FirebaseDatabase

fun setOperator(name: String, dni: String, address: String, country: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteNameOperator")
    refWrite.child("name").setValue(name)
    refWrite.child("dni").setValue(dni)
    refWrite.child("address").setValue(address)
    refWrite.child("country").setValue(country)
}

fun setConsignee(name: String, dni: String, address: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteNameConsignee")
    refWrite.child("name").setValue(name)
    refWrite.child("dni").setValue(dni)
    refWrite.child("address").setValue(address)
}

fun setDelivery(delivery: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteDelivery")
    refWrite.child("delivery").setValue(delivery)
}
fun setPicking(picking: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePicking")
    refWrite.child("picking").setValue(picking)
}
fun setPackageNumber(number:String){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePackage")
    refWrite.child("packageNumber").setValue(number)
}
fun setPacking(type: String){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePacking")
    refWrite.child("packaging").setValue(type)
}

fun setNatureKind(kind : MutableList<String>){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePackageKind")
    for (i in 0.. kind.size-1){
        refWrite.child(i.toString()).setValue(kind[i])
    }
}

fun setTotalWeight(weight: String){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteWeight")
    refWrite.child("totalWeight").setValue(weight)
}

fun setLicensePlate(license: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteLicense")
    refWrite.child("licensePlate").setValue(license)
}
fun setTrailerLicense(trailerLicense: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteTrailerLicense")
    refWrite.child("trailerLicense").setValue(trailerLicense)
}
fun setDriverName(name: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteDriverName")
    refWrite.child("driverName").setValue(name)
}

fun setPayment(pay: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WritePayment")
    refWrite.child("payment").setValue(pay)
}

fun setPaymentWay(kind: String, price: String? = null) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteKindPayment")
    refWrite.child("kind").setValue(kind)
    val refWrite2 = refName.child("WritePrice")
    refWrite2.child("price").setValue(price)
}

fun setRefund(refund: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteRefund")
    refWrite.child("refund").setValue(refund)
}

fun setDate(date: String) {
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteDate")
    refWrite.child("date").setValue(date)
}
fun setSign(sign: SnapshotStateList<Point>){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    val refWrite = refName.child("WriteSign")
    refWrite.removeValue()
    for (i in 0.. sign.size-1){
        refWrite.child(i.toString()).setValue(sign[i])
    }
}

fun clearDataBase(){
    val database = FirebaseDatabase.getInstance()
    val refName = database.getReference("Response")
    refName.removeValue()
}