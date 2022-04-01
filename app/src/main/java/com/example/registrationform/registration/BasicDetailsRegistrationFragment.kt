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
        //next or previous click events
        val onNextButtonClick:()->Unit={

        }
        val onPreviousButtonClick:()->Unit={

        }
        //dynamically set next prev buttons
        nextPreviousButtonsFormatting(
            binding.bottomNavLayout,
            binding.nextButton,
            binding.previousButton,
            onNextButtonClick,
            onPreviousButtonClick
        )
        val viewModel: BasicDetailsViewModel by viewModels()
        binding.viewModel = viewModel
        binding.nextButton.setOnClickListener {
            Log.d("first name",viewModel.firstName.value.toString())
            Log.d("last name",viewModel.lastName.value.toString())
        }
        //to here
        return binding.root
    }
}