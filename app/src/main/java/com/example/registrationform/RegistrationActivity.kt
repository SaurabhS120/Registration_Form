package com.example.registrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.registrationform.databinding.ActivityRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //all registration fragments will be handled by view model please refer before this
        val viewModel:RegistrationActivityViewModel = ViewModelProvider(this,ViewModelFactory(applicationContext)).get(RegistrationActivityViewModel::class.java)
        viewModel.currentFragment.observe(this){
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id,it)
                .commit()
            binding.headingTextView.text=it.getFragmentName()
            it.lifecycleScope.launchWhenCreated {
                binding.backButton.visibility =
                    if (it.hasPreviousFragment) View.VISIBLE
                    else View.INVISIBLE
            }
        }
        binding.backButton.setOnClickListener {
            viewModel.previousFragment()
        }
        viewModel.doneRegistration.observe(this){
            if (it){
                finish()
            }
        }
    }
}