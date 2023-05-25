package com.example.cartaportes.project.back.getters

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cartaportes.project.classes.Consignee
import com.example.cartaportes.project.classes.Point
import com.example.cartaportes.project.classes.User
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun getNameList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Users")
    val userList = mutableListOf<String>()

    usersRef.addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            dataSnapshot.child("name").getValue(String::class.java)?.let { userList.add(it) }
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return userList
}


fun getUserList(): MutableList<User> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Users")
    val usersList = mutableListOf<User>()

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (childSnapshot in dataSnapshot.children) {
                val name = childSnapshot.child("name").getValue(String::class.java)
                val dni = childSnapshot.child("dni").getValue(String::class.java)
                val address = childSnapshot.child("address").getValue(String::class.java)
                val country = childSnapshot.child("country").getValue(String::class.java)
                if (name != null && dni != null && address != null && country != null) {
                    usersList.add(User(name, dni, address, country))
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return usersList
}




fun getNameConsigList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Consignee")
    val userList = mutableListOf<String>()

    usersRef.addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            dataSnapshot.child("name").getValue(String::class.java)?.let { userList.add(it) }
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return userList
}


fun getConsigList(): MutableList<Consignee> {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Consignee")
    val consigList = mutableListOf<Consignee>()

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (childSnapshot in dataSnapshot.children) {
                val name = childSnapshot.child("name").getValue(String::class.java)
                val dni = childSnapshot.child("dni").getValue(String::class.java)
                val address = childSnapshot.child("address").getValue(String::class.java)
                if (name != null && dni != null && address != null) {
                    consigList.add(Consignee(name, dni, address))
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })

    return consigList
}

fun getDeliveryPlaces(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Delivery")

    val deliveryCompanies = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (deliveryCompany in dataSnapshot.children) {
                deliveryCompanies.add(deliveryCompany.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })

    return deliveryCompanies
}

fun getPickingPlaces(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Picking")

    val pickingCompanies = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (deliveryCompany in dataSnapshot.children) {
                pickingCompanies.add(deliveryCompany.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })
    return  pickingCompanies
}

fun getVehicleList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Vehicle")

    val vehicleList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (vehicle in dataSnapshot.children) {
                vehicleList.add(vehicle.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })
    return vehicleList
}
fun getTrailerList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Trailer")

    val trailerList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (vehicle in dataSnapshot.children) {
                trailerList.add(vehicle.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })
    return trailerList
}

fun getDriverList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("DriverName")

    val trailerList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (vehicle in dataSnapshot.children) {
                trailerList.add(vehicle.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })
    return trailerList
}

fun getPayerList(): MutableList<String> {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Payer")

    val payerList = mutableListOf<String>()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (payer in dataSnapshot.children) {
                payerList.add(payer.getValue(String::class.java)!!)
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    })

    return payerList
}

// OPERATOR
fun getOperatorName(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val name = dataSnapshot.child("name").getValue(String::class.java) ?: ""
            callback(name)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getOperatorDni(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val dni = dataSnapshot.child("dni").getValue(String::class.java) ?: ""
            callback(dni)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getOperatorAddress(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val address = dataSnapshot.child("address").getValue(String::class.java) ?: ""
            callback(address)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}
fun getOperatorCountry(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameOperator")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val country = dataSnapshot.child("country").getValue(String::class.java) ?: ""
            callback(country)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getConsigneeName(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameConsignee")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val name = dataSnapshot.child("name").getValue(String::class.java) ?: ""
            callback(name)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}
fun getConsigneeDni(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameConsignee")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val dni = dataSnapshot.child("dni").getValue(String::class.java) ?: ""
            callback(dni)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}
fun getConsigneeAddress(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteNameConsignee")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val address = dataSnapshot.child("address").getValue(String::class.java) ?: ""
            callback(address)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getDelivery(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteDelivery")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val delivery = dataSnapshot.child("delivery").getValue(String::class.java) ?: ""
            callback(delivery)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getPicking(callback: (String) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePicking")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val picking = dataSnapshot.child("picking").getValue(String::class.java) ?: ""
            callback(picking)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getPackage(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePackage")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val packageNumber = dataSnapshot.child("packageNumber").getValue(String::class.java) ?: ""
            callback(packageNumber)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getPacking(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePacking")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val packaging = dataSnapshot.child("packaging").getValue(String::class.java) ?: ""
            callback(packaging)
        }
        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getPackageKind(): MutableList<String> {
    val list = mutableListOf<String>()
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePackageKind")
    ref.addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            var packcageKind = dataSnapshot.getValue(String::class.java)!!
            list.add(packcageKind)
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
    return list
}

fun getTotalWeight(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteWeight")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val weight = dataSnapshot.child("totalWeight").getValue(String::class.java) ?: ""
            callback(weight)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}
fun getLicense(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteLicense")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val license = dataSnapshot.child("licensePlate").getValue(String::class.java) ?: ""
            callback(license)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}
fun getTrailerLicense(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteTrailerLicense")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val license = dataSnapshot.child("trailerLicense").getValue(String::class.java) ?: ""
            callback(license)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}
fun getDriverName(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteDriverName")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val license = dataSnapshot.child("driverName").getValue(String::class.java) ?: ""
            callback(license)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getPayment(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePayment")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val payment = dataSnapshot.child("payment").getValue(String::class.java) ?: ""
            callback(payment)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getPaymentKind(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteKindPayment")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val kind = dataSnapshot.child("kind").getValue(String::class.java) ?: ""
            callback(kind)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}
fun getPrice(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WritePrice")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val price = dataSnapshot.child("price").getValue(String::class.java) ?: "0"
            callback(price)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getRefund(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteRefund")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val refund = dataSnapshot.child("refund").getValue(String::class.java) ?: ""
            callback(refund)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}

fun getDate(callback: (String) -> Unit){
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteDate")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val date = dataSnapshot.child("date").getValue(String::class.java) ?: ""
            callback(date)
        }

        override fun onCancelled(databaseError: DatabaseError) {
        }
    })
}



// SIGN
fun getSign(callback: (SnapshotStateList<Point>) -> Unit) {
    val ref = FirebaseDatabase.getInstance().getReference("Response/WriteSign")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val sign = SnapshotStateList<Point>()
            dataSnapshot.children.forEach {
                val point = it.getValue(Point::class.java)
                if (point != null) sign.add(point)
            }
            callback(sign)
        }
        override fun onCancelled(error: DatabaseError) {
        }
    })
}
