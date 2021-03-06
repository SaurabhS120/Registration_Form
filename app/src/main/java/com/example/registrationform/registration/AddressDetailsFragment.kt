package com.example.registrationform.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.registrationform.R
import com.example.registrationform.RegistrationActivity
import com.example.registrationform.ViewModelFactory
import com.example.registrationform.databinding.FragmentAddressDetailsBinding
import com.example.registrationform.databinding.FragmentAddressDetailsConstraintLayoutBinding
import com.example.registrationform.registration.data.AddressDetailsData
import java.util.regex.Pattern
import javax.inject.Inject

class AddressDetailsFragment @Inject constructor() : RegistrationFragment() {
    override fun getFragmentName(): String = "Your addresses"
    private lateinit var binding:FragmentAddressDetailsConstraintLayoutBinding
    private lateinit var viewModel: AddressDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddressDetailsConstraintLayoutBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(activity as RegistrationActivity, ViewModelFactory(requireContext())).get(AddressDetailsViewModel::class.java)
        this.viewModel = viewModel
        binding.viewModel = viewModel
        //next or previous click events
        val onNextButtonClick:()->Boolean={
            val isValid = isDataValid()
            if (isValid) saveData()
            isValid
        }
        val onPreviousButtonClick:()->Boolean={
            val isValid = isDataValid()
            if (isValid) saveData()
            isValid
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
    fun isDataValid():Boolean{
        var isValid = true
        if (isAddressValid().not())isValid = false
        if (isLandmarkValid().not())isValid = false
        if (isCityValid().not())isValid = false
        if (isPinCodeValid().not())isValid = false
        if (isStateValid().not())isValid = false
        return isValid
    }
    fun isAddressValid():Boolean{
        val address = viewModel.address.value as CharSequence
        if (Pattern.matches("^[0-9a-zA-Z ,]{3,}",address)){
            binding.addressEditText.error = null
            return true
        }else{
            binding.addressEditText.error = "Address should contain more than 3 characters"
            return false
        }
    }
    fun isLandmarkValid():Boolean{
        val landmark = viewModel.landmark.value as CharSequence
        if (Pattern.matches("^[0-9a-zA-Z ,]{3,}",landmark)){
            binding.landmarkEditText.error = null
            return true
        }else{
            binding.landmarkEditText.error = "Landmark should contain more than 3 characters"
            return false
        }
    }
    fun isCityValid():Boolean{
        val city = viewModel.city.value as CharSequence
        if (Pattern.matches("^[a-zA-Z ,]{3,}",city)){
            binding.cityEditText.error = null
            return true
        }else{
            binding.cityEditText.error = "City should contain more than 3 characters"
            return false
        }
    }
    fun isPinCodeValid():Boolean{
        val pinCode = viewModel.pinCode.value as CharSequence
        if (Pattern.matches("^[0-9]{6}",pinCode)){
            binding.pinCodeEditText.error = null
            return true
        }else{
            binding.pinCodeEditText.error = "Pin code should contain 6 numbers"
            return false
        }
    }
    fun isStateValid():Boolean{
        val state = viewModel.state.value as CharSequence
        if (Pattern.matches("Select Your State",state)){
            binding.stateErrorTextView.visibility = View.VISIBLE
            return false
        }else{
            binding.stateErrorTextView.visibility = View.INVISIBLE
            return true
        }
    }
    fun saveData(){
        val data = with(viewModel){
            AddressDetailsData(
                0,
                address.value.toString(),
                landmark.value.toString(),
                city.value.toString(),
                state.value.toString(),
                pinCode.value.toString()
            )
        }
        setData(data)
    }
}