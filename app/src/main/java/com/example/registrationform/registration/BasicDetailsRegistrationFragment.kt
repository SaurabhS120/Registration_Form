package com.example.registrationform.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        //dynamically set next prev buttons
        nextPreviousButtonsFormatting(binding.bottomNavLayout,binding.nextButton,binding.previousButton)
        //to here
        return binding.root
    }
}