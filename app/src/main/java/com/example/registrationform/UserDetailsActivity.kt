package com.example.registrationform

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.example.registrationform.databinding.ActivityUserDetailsBinding
import com.example.registrationform.registration.data.UserDetailsData
import java.io.ByteArrayInputStream

class UserDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userDetailsData = intent.extras!!.getParcelable<UserDetailsData>("user")!!

        //basic details assign
        binding.firstNameTextView.setText(userDetailsData.basicRegistrationDetailsData.firstName)
        binding.lastNameTextView.setText(userDetailsData.basicRegistrationDetailsData.lastName)
        binding.phoneNoTextView.setText(userDetailsData.basicRegistrationDetailsData.phoneNo)
        binding.emailTextView.setText(userDetailsData.basicRegistrationDetailsData.email)
        binding.genderTextView.setText(userDetailsData.basicRegistrationDetailsData.gender)

        //education details assign
        binding.educationDetailsTextView.setText(userDetailsData.educationDetailsData.education)
        binding.passingYearTextView.setText(userDetailsData.educationDetailsData.year_of_passing)
        binding.gradeTextView.setText(userDetailsData.educationDetailsData.grade)
        binding.experienceTextView.setText(userDetailsData.educationDetailsData.experience)
        binding.designationTextView.setText(userDetailsData.educationDetailsData.designation)
        binding.domainTextView.setText(userDetailsData.educationDetailsData.domain)

        //address details assign
        binding.addressTextView.setText(userDetailsData.addressDetailsData.address)
        binding.landmarkTextView.setText(userDetailsData.addressDetailsData.landmark)
        binding.cityTextView.setText(userDetailsData.addressDetailsData.city)
        binding.stateTextView.setText(userDetailsData.addressDetailsData.state)
        binding.pinCodeTextView.setText(userDetailsData.addressDetailsData.pinCode)
        val byteArrayNew:ByteArray = Base64.decode(userDetailsData.basicRegistrationDetailsData.profilePhoto, Base64.DEFAULT)
        val inputStream = ByteArrayInputStream(byteArrayNew)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        binding.profilePhotoImageView.setImageBitmap(bitmap)

    }
}