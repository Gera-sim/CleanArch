package com.example.cleanarch.domain.usecase

import com.example.cleanarch.domain.models.SaveUserNameParam

class SaveUserNameUseCase {
    fun execute (param: SaveUserNameParam) : Boolean {
        if(param.name.isEmpty()){
            return false
        } else{
            return true
        }
    }
}