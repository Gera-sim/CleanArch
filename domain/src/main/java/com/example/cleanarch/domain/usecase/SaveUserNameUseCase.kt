package com.example.cleanarch.domain.usecase

import com.example.cleanarch.domain.models.SaveUserNameParam
import com.example.cleanarch.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {
    fun execute(param: SaveUserNameParam): Boolean {
        val result: Boolean = userRepository.saveName(saveParam = param)
        return result
    }
}