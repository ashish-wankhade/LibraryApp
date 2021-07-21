package com.example.library

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class FirestoreActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var etfName: EditText
    lateinit var etlName: EditText
    lateinit var etfYear: EditText

    var TAG = FirestoreActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firestore)
        etfName = findViewById(R.id.etFirst)
        etlName = findViewById(R.id.etLast)
        etfYear = findViewById(R.id.etBorn)

        db = FirebaseFirestore.getInstance()
    }

    fun handleFireDb(view: View) {
        sendDataFirebase()
    }

    private fun sendDataFirebase() {
        var firstName =etfName.text.toString()
        var lastName = etlName.text.toString()
        var birthYear = etfYear.text.toString()
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = firstName
        user["last"] = lastName
        user["born"] = birthYear

// Add a new document with a generated ID

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
        etfName.setText("")
        etlName.setText("")
        etfYear.setText("")
    }
}