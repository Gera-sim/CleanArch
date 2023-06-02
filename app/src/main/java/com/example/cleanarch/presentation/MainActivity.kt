package com.example.cleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cleanarch.R
import com.example.cleanarch.data.repository.UserRepositoryImpl
import com.example.cleanarch.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.cleanarch.domain.models.SaveUserNameParam
import com.example.cleanarch.domain.models.UserName
import com.example.cleanarch.domain.usecase.GetUserNameUseCase
import com.example.cleanarch.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {

    private val userRepository by lazy(LazyThreadSafetyMode.NONE){
        UserRepositoryImpl(
            userStorage = SharedPrefUserStorage(
                context = applicationContext
            )
        )
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(
            userRepository = userRepository
        )
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(
            userRepository = userRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<TextView>(R.id.editText)
        val getData = findViewById<Button>(R.id.getData)
        val setData = findViewById<Button>(R.id.setData)

        setData.setOnClickListener {
            val text = editText.text.toString()
            val params = SaveUserNameParam(name = text)
            val result: Boolean = saveUserNameUseCase.execute(param = params)
            textView.text = "Save result = $result"
        }

        getData.setOnClickListener {
            val userName: UserName = getUserNameUseCase.execute()
            textView.text = "${userName.firstName} ${userName.lastName}"
        }
    }
}