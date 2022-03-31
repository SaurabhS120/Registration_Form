package com.example.registrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.registrationform.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //all registration fragments will be handled by view model please refer before this
        val viewModel:MainActivityViewModel by viewModels()
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
    }
}