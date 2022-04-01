package com.example.registrationform.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.registrationform.databinding.FragmentAddressDetailsBinding

class AddressDetailsFragment : RegistrationFragment() {
    override fun getFragmentName(): String = "Your addresses"
    private lateinit var binding:FragmentAddressDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddressDetailsBinding.inflate(layoutInflater)//next or previous click events
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
        return binding.root
    }
}