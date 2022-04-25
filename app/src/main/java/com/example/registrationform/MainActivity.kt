package com.example.registrationform

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.registrationform.databinding.ActivityMainBinding
import com.example.registrationform.location.LocationViewModel
import com.example.registrationform.registration.data.UserDetailsData
import com.example.registrationform.room.UsersDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var users: LiveData<List<UserDetailsData>>
    lateinit var locationViewModel: LocationViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                Toast.makeText(this,"Fine Location permission granted",Toast.LENGTH_SHORT).show()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                Toast.makeText(this,"Course Location permission granted",Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this,"Location permission is not granted",Toast.LENGTH_SHORT).show()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationViewModel = ViewModelFactory(this).create(LocationViewModel::class.java)
        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        binding.usersRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = UserRecyclerAdapter(baseContext)
        binding.usersRecyclerView.adapter = adapter
        users = UsersDatabase.getDatabase(this).usersDao().getUsers()
        users.observe(this) {
            adapter.updateUsers(it)
        }
        enableLocation()
        locationViewModel.startLocationWorker(application)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun enableLocation() {

        // ...

        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }
}