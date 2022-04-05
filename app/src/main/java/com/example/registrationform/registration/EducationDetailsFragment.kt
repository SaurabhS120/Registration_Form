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

                viewModel.year_of_passing.postValue(resources.getStringArray(R.array.education)[pos])

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
        return binding.root
    }
}