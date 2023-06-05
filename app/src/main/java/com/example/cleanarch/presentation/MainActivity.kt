package com.example.cleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarch.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("AAA","Activity created")

//        1 способ создания (не правильный)
//        vm = MainViewModel()
//        2 способ создания (правильный)
        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<TextView>(R.id.editText)
        val getData = findViewById<Button>(R.id.getData)
        val setData = findViewById<Button>(R.id.setData)

        vm.getResultLive().observe(this, Observer { text ->
            textView.text = text
        })

        //от LiveData отписываться не нужно - она сама контролирует жизненный цикл

        setData.setOnClickListener {
            val text = editText.text.toString()
            vm.save(text)
        }

        getData.setOnClickListener {
            vm.load()
        }
    }
}