package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _users.value = getUsersUseCase()
            } catch (e: Exception) {
                _error.value = "An error occurred: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}