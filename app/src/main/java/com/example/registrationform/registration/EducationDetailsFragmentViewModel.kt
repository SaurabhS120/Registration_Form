package com.example.registrationform.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EducationDetailsFragmentViewModel:ViewModel() {
    val education = MutableLiveData("Select Your Qualification")
    val year_of_passing = MutableLiveData("Enter year of passing")
    val grade = MutableLiveData("")
    val experience = MutableLiveData("")
    val designation = MutableLiveData("")
    val domain = MutableLiveData("")
}