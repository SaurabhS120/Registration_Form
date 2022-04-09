package com.example.registrationform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.registrationform.databinding.ActivityMainBinding
import com.example.registrationform.registration.data.UserDetailsData
import com.example.registrationform.room.UsersDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var users:LiveData<List<UserDetailsData>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val adapter = UserRecyclerAdapter(baseContext)
        binding.usersRecyclerView.adapter = adapter
        users = UsersDatabase.getDatabase(this).usersDao().getUsers()
        users.observe(this){
            adapter.updateUsers(it)
        }

    }
}