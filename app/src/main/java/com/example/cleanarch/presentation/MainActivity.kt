package com.example.cleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.cleanarch.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("AAA","Activity created")


        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<TextView>(R.id.editText)
        val getData = findViewById<Button>(R.id.getData)
        val setData = findViewById<Button>(R.id.setData)

        vm.resultLive.observe(this, Observer { text ->
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