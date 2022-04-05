package com.example.registrationform.registration

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.registrationform.databinding.FragmentBasicDetailsRegistrationBinding
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import java.util.regex.Pattern

class BasicDetailsRegistrationFragment : RegistrationFragment() {
    override fun getFragmentName(): String = "Register"
    private lateinit var binding:FragmentBasicDetailsRegistrationBinding
    private lateinit var viewModel: BasicDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentBasicDetailsRegistrationBinding.inflate(layoutInflater)
        val viewModel: BasicDetailsViewModel by activityViewModels()
        this.viewModel=viewModel
        binding.viewModel = viewModel
        //next or previous click events
        val onNextButtonClick:()->Boolean={
            viewModel.log()
            val isValid = isDataValid()
            if (isValid){
                saveData()
            }
            isValid
        }
        val onPreviousButtonClick:()->Boolean={
            viewModel.log()
            isDataValid()
        }
        //dynamically set next prev buttons
        nextPreviousButtonsFormatting(
            binding.bottomNavLayout,
            binding.nextButton,
            binding.previousButton,
            onNextButtonClick,
            onPreviousButtonClick
        )
        binding.maleRadioButton.setOnClickListener {
            viewModel.gender.postValue("Male")
        }
        binding.femaleRadioButton.setOnClickListener {
            viewModel.gender.postValue("Female")
        }
        viewModel.gender.observe(viewLifecycleOwner){
            if(it.equals("Male")){
                viewModel.isMale.postValue(true)
                binding.maleRadioButton.isChecked = true
            }
            else if (it.equals("Female")){
                viewModel.isFemale.postValue(true)
                binding.femaleRadioButton.isChecked = true
            }
        }
        return binding.root
    }

    private fun saveData() {
        val data = BasicRegistrationDetailsData(
            viewModel.firstName.value?:"",
            viewModel.lastName.value?:"",
            viewModel.phoneNo.value?:"",
            viewModel.email.value?:"",
            viewModel.gender.value?:"",
            viewModel.password.value?:"",
            viewModel.confirmPassword.value?:"",
        )
        Log.d("info","first name : ${data.firstName}")
        setData(data)
    }

    private fun isDataValid(): Boolean {
        var valid=true
        if (isValidFirstName().not())           valid=false
        if (isValidLastName().not())            valid=false
        if (isValidEmail().not())               valid=false
        if (isValidPassword().not())            valid=false
        if (isValidConfirmPassword().not())     valid=false
        if (isValidGender().not())              valid=false
        if (isValidPhoneNo().not())            valid=false
        return valid
    }

    private fun isValidFirstName(): Boolean {
        if(Pattern.matches("[a-z|A-Z]{3,}",viewModel.firstName.value as CharSequence)){
            return true
        }else{
            binding.firstNameEditText.error = "Please enter at least 3 characters in this field"
        }
        return false
    }
    private fun isValidLastName(): Boolean {
        if(Pattern.matches("[a-z|A-Z]{3,}",viewModel.lastName.value as CharSequence)){
            return true
        }else{
            binding.lastNameEditText.error = "Please enter at least 3 characters in this field"
        }
        return false
    }
    private fun isValidEmail():Boolean{
        if (Patterns.EMAIL_ADDRESS.matcher(viewModel.email.value as CharSequence).matches()){
            return true
        }else{
            binding.emailEditText.error = "Please enter valid email address"
        }
        return false
    }
    private fun isValidPassword():Boolean{
        if (Pattern.matches("^(?=.*[a-zA-Z])(?=.*?[0-9])(?=.*[@\$!%*#?&]).{8,}$",viewModel.password.value as CharSequence)){
            return true
        }else{
            binding.passwordEditText.error = "Password should contain characters, number and special character (@\$!%*#?&)"
        }
        return false
    }
    private fun isValidConfirmPassword():Boolean{
        if (viewModel.password.value.equals(viewModel.confirmPassword.value)){
            return true
        }else{
            binding.confirmPasswordEditText.error = "Password should be same"
        }
        return false

    }
    private fun isValidGender():Boolean{
        if (Pattern.matches("Male|Female",viewModel.gender.value as CharSequence)){
            binding.genderErrorText.visibility = View.INVISIBLE
            return true
        }else{
            binding.genderErrorText.visibility = View.VISIBLE
        }
        return false
    }
    private fun isValidPhoneNo(): Boolean {
        if(Pattern.matches("[0-9]{10}",viewModel.phoneNo.value as CharSequence)){
            return true
        }else{
            binding.phoneNoEditText.error = "Please enter 10 digit mobile no"
        }
        return false
    }
}