package com.example.kotlinhelloworld

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import java.util.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Button = findViewById<Button>(R.id.Button)
        val resultsTextView = findViewById<TextView>(R.id.resultsTextview)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        Button.setOnClickListener {
            val rand = Random().nextInt(seekBar.progress) + 1
            resultsTextView.text = rand.toString()
        }

    }
}