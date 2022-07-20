package com.example.jetpackdatastore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.jetpackdatastore.PrefsDataStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var Prefs: PrefsDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val save: Button = findViewById(R.id.Save)
        val read: Button = findViewById(R.id.read)
        val proto: Button = findViewById(R.id.ProtoData)
        val edittext: EditText = findViewById(R.id.editTextTextPersonName)
        val textToShow: TextView = findViewById(R.id.textToShow)

        // Passing the ref of activity
        Prefs = PrefsDataStore(this)


        //Whenever we open our app it will fetch the data & assign it
        GlobalScope.launch {
            val value = Prefs.getD("temps")
            textToShow.setText(value)
        }


        save.setOnClickListener {
            GlobalScope.launch {
                Prefs.storeData(
                    "temps",
                    edittext.text.toString()
                )  // Temps is the key which we are passing
            }
        }

        read.setOnClickListener {
            lifecycleScope.launch {
                val value = Prefs.getD("temps") // Temps is the key which we are passing
                textToShow.setText(value)
            }
        }

        proto.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
