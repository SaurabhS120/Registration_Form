package com.example.registrationform.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.registrationform.R
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
        val onNextButtonClick:()->Boolean={
            true
        }
        val onPreviousButtonClick:()->Boolean={
            true
        }
        //dynamically set next prev buttons
        nextPreviousButtonsFormatting(
            binding.bottomNavLayout,
            binding.nextButton,
            binding.previousButton,
            onNextButtonClick,
            onPreviousButtonClick
        )

        //state spinner adapter
        ArrayAdapter.createFromResource(requireContext(),
            R.array.states,
            android.R.layout.simple_spinner_dropdown_item
        ).also { arrayAdapter ->

            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.stateSpinner.adapter = arrayAdapter
        }
        return binding.root
    }
}