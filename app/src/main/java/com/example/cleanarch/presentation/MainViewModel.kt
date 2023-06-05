package com.example.cleanarch.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarch.domain.models.SaveUserNameParam
import com.example.cleanarch.domain.models.UserName
import com.example.cleanarch.domain.usecase.GetUserNameUseCase
import com.example.cleanarch.domain.usecase.SaveUserNameUseCase

class MainViewModel(
    private val getUserNameUseCase:GetUserNameUseCase,
    private val saveUserNameUseCase:SaveUserNameUseCase
    ) : ViewModel() {
// ViewModel не должна иметь контекст!!!!!

    private val resultLive = MutableLiveData<String>()

init{
    Log.e("AAA","VM created")}

    override fun onCleared() {
        Log.e("AAA","VM cleared")
        super.onCleared()
    }

    fun getResultLive():LiveData<String>{
        return resultLive
    }

    fun save(text:String){
        val params = SaveUserNameParam(name = text)
        val resultData: Boolean = saveUserNameUseCase.execute(param = params)
        resultLive.value = "Save result = $resultData"
    }

    fun load(){
        val userName: UserName = getUserNameUseCase.execute()
        resultLive.value = "${userName.firstName} ${userName.lastName}"
    }

}