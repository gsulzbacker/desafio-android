package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.network.PicPayService

class UserRepository(
    private val picPayService: PicPayService
) {

    suspend fun getUsers(): List<User> {
        return picPayService.getUsers()
    }
}