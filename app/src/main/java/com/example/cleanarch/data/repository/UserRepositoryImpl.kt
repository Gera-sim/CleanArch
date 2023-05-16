package com.example.cleanarch.data.repository

import com.example.cleanarch.data.storage.models.User
import com.example.cleanarch.data.storage.UserStorage
import com.example.cleanarch.domain.models.SaveUserNameParam
import com.example.cleanarch.domain.models.UserName
import com.example.cleanarch.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        val user = mapToStorage(saveParam)
        val result = userStorage.save(user)
        return result
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam) :User{
        return User(firstName = saveParam.name, lastName = "")
    }

    private fun mapToDomain(user: User) :UserName{
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }
}