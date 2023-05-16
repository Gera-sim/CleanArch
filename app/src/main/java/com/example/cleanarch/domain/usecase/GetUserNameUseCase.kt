package com.example.cleanarch.domain.usecase

import com.example.cleanarch.domain.models.UserName

class GetUserNameUseCase {
    fun execute():UserName {
return UserName(firstName = "Vasya", lastName = "Hello!")
    }
}