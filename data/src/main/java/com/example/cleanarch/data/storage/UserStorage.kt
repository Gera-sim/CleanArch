package com.example.cleanarch.data.storage

import com.example.cleanarch.data.storage.models.User

interface UserStorage {

    fun save(user: User): Boolean

    fun get(): User
}