package com.example.registrationform.registration

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.InputType
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.registrationform.RegistrationActivity
import com.example.registrationform.ViewModelFactory
import com.example.registrationform.databinding.FragmentBasicDetailsRegistrationBinding
import com.example.registrationform.databinding.FragmentBasicDetailsRegistrationConstraintLayoutBinding
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.regex.Pattern
import javax.inject.Inject

class BasicDetailsRegistrationFragment @Inject constructor() : RegistrationFragment() {
    override fun getFragmentName(): String = "Register"
    private lateinit var binding:FragmentBasicDetailsRegistrationConstraintLayoutBinding
    private lateinit var viewModel: BasicDetailsViewModel
    val resultContract = registerForActivityResult(ActivityResultContracts.GetContent()){
        it?.let {
            var encodedProfilePhoto = ""
            val image_stream = requireContext().contentResolver.openInputStream(it)
            var bitmap = BitmapFactory.decodeStream(image_stream)
            bitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, false)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray: ByteArray = stream.toByteArray()
            encodedProfilePhoto = Base64.encodeToString(byteArray, Base64.DEFAULT)
            Log.d("base64", encodedProfilePhoto)
            viewModel.profilePhoto.postValue(encodedProfilePhoto)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentBasicDetailsRegistrationConstraintLayoutBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(activity as RegistrationActivity,ViewModelFactory(requireContext())).get(BasicDetailsViewModel::class.java)
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
            val isValid = isDataValid()
            if (isValid){
                saveData()
            }
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
        binding.profileLayout.setOnClickListener {
            selectProfile()
        }
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
        viewModel.profilePhoto.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){

                val byteArrayNew:ByteArray = Base64.decode(it, Base64.DEFAULT)
                val inputStream = ByteArrayInputStream(byteArrayNew)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.profilePhotoImageView.setImageBitmap(bitmap)
            }
        }
        binding.showPasswordButton.setOnClickListener {
            if (binding.passwordEditText.inputType != InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD ){
                binding.passwordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }else {
                binding.passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
        }
        return binding.root
    }

    private fun saveData() {
        val data = BasicRegistrationDetailsData(
            0,
            viewModel.profilePhoto.value ?: "",
            viewModel.firstName.value?:"",
            viewModel.lastName.value?:"",
            viewModel.phoneNo.value?:"",
            viewModel.email.value?:"",
            viewModel.gender.value?:"",
            viewModel.password.value?:""
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
        if (isProfilePhotoSelected().not())            valid=false
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
    private fun selectProfile(){
        resultContract.launch("image/jpeg")
    }

    private fun isProfilePhotoSelected():Boolean{
        if (viewModel.profilePhoto.value?.length?:0 >0){
            binding.profilePhotoErrorText.visibility = View.INVISIBLE
            return true
        }else{
            binding.profilePhotoErrorText.visibility = View.VISIBLE
        }
        return false
    }
}