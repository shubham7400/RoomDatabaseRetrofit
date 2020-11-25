package com.example.userlist

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var fabInMain: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabInMain = findViewById<FloatingActionButton>(R.id.fabInMainId)
        fabInMain.setOnClickListener{
            val intent = Intent(this, AddNewUserActivity::class.java)
            startActivity(intent)
        }
    }
}