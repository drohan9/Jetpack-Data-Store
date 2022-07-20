package com.example.jetpackdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity2 : AppCompatActivity() {
    lateinit var viewModelMain: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val textToShow: TextView = findViewById(R.id.textToShow2)
        val editTextTextPersonName2: EditText = findViewById(R.id.editTextTextPersonName2)
        val save: Button = findViewById(R.id.Save2)
        viewModelMain = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModelMain.text.observe(this,{
            textToShow.setText(it.msg)
        })
        save.setOnClickListener {
            viewModelMain.updateValue(editTextTextPersonName2.text.toString())
        }
    }
    }