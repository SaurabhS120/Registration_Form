package com.example.registrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrationform.databinding.ActivityUserDetailsBinding
import com.example.registrationform.registration.data.UserDetailsData

class UserDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userDetailsData = UserDetailsData.createFromParcel(intent.extras!!.getParcelable("data")!!)

    }
}