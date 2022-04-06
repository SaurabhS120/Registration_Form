package com.example.registrationform.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.registrationform.R
import com.example.registrationform.databinding.FragmentEducationDetailsBinding
import com.example.registrationform.registration.data.EducationDetailsData
import java.util.regex.Pattern

class EducationDetailsFragment : RegistrationFragment() {
    override fun getFragmentName(): String = "Your Info"
    private lateinit var binding:FragmentEducationDetailsBinding
    lateinit var viewModel: EducationDetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEducationDetailsBinding.inflate(layoutInflater)
        val viewModel : EducationDetailsFragmentViewModel by activityViewModels()
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
        //education spinner
        ArrayAdapter.createFromResource(requireContext(),
        R.array.education,
        android.R.layout.simple_spinner_dropdown_item
        ).also { arrayAdapter ->

            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.educationSpinner.adapter = arrayAdapter
        }
        binding.educationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {

                viewModel.education.postValue(resources.getStringArray(R.array.education)[pos])

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
        //year of passing spinner
        ArrayAdapter.createFromResource(requireContext(),
            R.array.year_of_passing,
            android.R.layout.simple_spinner_dropdown_item
        ).also { arrayAdapter ->

            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.passingYearSpinner.adapter = arrayAdapter
        }
        binding.passingYearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {

                viewModel.year_of_passing.postValue(resources.getStringArray(R.array.year_of_passing)[pos])

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
        return binding.root
    }
    fun isDataValid():Boolean{
        var isValid =true
        if (isEducationSelected().not()) isValid = false
        if (isPassingYearSelected().not()) isValid = false
        if (isGradeValid().not()) isValid = false
        if (isExperienceValid().not()) isValid = false
        if (isDesignationValid().not()) isValid = false
        if (isDomainValid().not()) isValid = false
        return isValid
    }
    fun isEducationSelected():Boolean{
        val valid = viewModel.education.value.equals("Select Your Qualification").not()
        if (valid.not()){
            binding.educationErrorText.visibility = View.VISIBLE
        }else{
            binding.educationErrorText.visibility = View.INVISIBLE
        }
        return valid
    }
    fun isPassingYearSelected():Boolean{
        val valid = viewModel.year_of_passing.value.equals("Enter year of passing").not()
        if (valid.not()){
            binding.passingYearErrorText.visibility = View.VISIBLE
        }else{
            binding.passingYearErrorText.visibility = View.INVISIBLE
        }
        return valid
    }
    fun isGradeValid():Boolean{
        val valid = Pattern.matches("^[a-zA-z0-9%]+",viewModel.grade.value as CharSequence)
        if (valid.not()){
            binding.gradeErrorText.visibility = View.VISIBLE
        }else{
            binding.gradeErrorText.visibility = View.INVISIBLE
        }
        return valid
    }
    fun isExperienceValid():Boolean{
        val valid = Pattern.matches("^[0-9]+",viewModel.experience.value as CharSequence)
        if (valid.not()){
            binding.experienceErrorText.visibility = View.VISIBLE
        }else{
            binding.experienceErrorText.visibility = View.INVISIBLE
        }
        return valid
    }
    fun isDesignationValid():Boolean{
        val valid = Pattern.matches("^[a-zA-z0-9]+",viewModel.designation.value as CharSequence)
        if (valid.not()){
            binding.designationErrorText.visibility = View.VISIBLE
        }else{
            binding.designationErrorText.visibility = View.INVISIBLE
        }
        return valid
    }
    fun isDomainValid():Boolean{
        val valid = Pattern.matches("^[a-zA-z0-9]+",viewModel.domain.value as CharSequence)
        if (valid.not()){
            binding.domainErrorText.visibility = View.VISIBLE
        }else{
            binding.domainErrorText.visibility = View.INVISIBLE
        }
        return valid
    }
    fun saveData(){
        val data =with(viewModel) {
             EducationDetailsData(
                 education.value.toString(),
                 year_of_passing.value.toString(),
                 grade.value.toString(),
                 experience.value.toString(),
                 domain.value.toString(),
                 designation.value.toString()
            )
        }
        setData(data)
    }

}