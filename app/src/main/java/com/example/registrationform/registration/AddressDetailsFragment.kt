package com.example.registrationform.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.registrationform.R
import com.example.registrationform.databinding.FragmentAddressDetailsBinding

class AddressDetailsFragment : RegistrationFragment() {
    override fun getFragmentName(): String = "Your addresses"
    private lateinit var binding:FragmentAddressDetailsBinding
    private lateinit var viewModel: AddressDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddressDetailsBinding.inflate(layoutInflater)
        val viewModel:AddressDetailsViewModel by activityViewModels()
        this.viewModel = viewModel
        binding.viewModel = viewModel
        //next or previous click events
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
        //state name view model binding
        binding.stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                viewModel.state.postValue(resources.getStringArray(R.array.states)[pos])
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        return binding.root
    }
}