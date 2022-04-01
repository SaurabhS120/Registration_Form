package com.example.registrationform.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.registrationform.databinding.FragmentEducationDetailsBinding

class EducationDetailsFragment : RegistrationFragment() {
    override fun getFragmentName(): String = "Your Info"
    private lateinit var binding:FragmentEducationDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEducationDetailsBinding.inflate(layoutInflater)
        //dynamically set next prev buttons
        nextPreviousButtonsFormatting(binding.bottomNavLayout,binding.nextButton,binding.previousButton)
        return binding.root
    }
}