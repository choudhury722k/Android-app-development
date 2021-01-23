package com.example.birthdaygreet


import android.widget.Toast
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.view.View
import android.widget.TextView
import java.util.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createBirthdatCard(view: View) {
        val name = findViewById<EditText>(R.id.entername)
        val name1 = name.editableText.toString()

        val intent = Intent(this, BirthdayGreetingActivity::class.java)
        intent.putExtra(BirthdayGreetingActivity.NAME_EXTRA, name1)
        startActivity(intent)
        //intent is used to go to other page like sharing meme or showing another window

    }
}


