package com.example.registrationform.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.registrationform.databinding.FragmentBasicDetailsRegistrationBinding

class BasicDetailsRegistrationFragment : RegistrationFragment() {
    override fun getFragmentName(): String = "Register"
    private lateinit var binding:FragmentBasicDetailsRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentBasicDetailsRegistrationBinding.inflate(layoutInflater)
        val viewModel: BasicDetailsViewModel by viewModels()
        binding.viewModel = viewModel
        //next or previous click events
        val onNextButtonClick:()->Unit={
            viewModel.log()
        }
        val onPreviousButtonClick:()->Unit={
            viewModel.log()
        }
        //dynamically set next prev buttons
        nextPreviousButtonsFormatting(
            binding.bottomNavLayout,
            binding.nextButton,
            binding.previousButton,
            onNextButtonClick,
            onPreviousButtonClick
        )
        //to here
        binding.nextButton.setOnClickListener {
            viewModel.log()
        }
        return binding.root
    }
}