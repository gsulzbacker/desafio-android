package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.domain.repository.UserRepository

class GetUsersUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}