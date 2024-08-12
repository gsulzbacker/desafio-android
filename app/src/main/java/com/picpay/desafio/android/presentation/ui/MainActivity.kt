package com.picpay.desafio.android.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: UserViewModel by viewModel()
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.users.observe(this) { users ->
            adapter.users = users
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        viewModel.loadUsers()
    }
}
