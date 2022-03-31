package com.example.registrationform.registration

import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.registrationform.MainActivityViewModel

abstract class RegistrationFragment : Fragment() {
    //all registration fragments are only for main activity
    //every variable will be assigned by main activity view model
    //all functions will be used by child fragments
    abstract fun getFragmentName():String
    private lateinit var activityMainViewModel: MainActivityViewModel
    var hasNextFragment=true
    var hasPreviousFragment=true
    var textForNextButton = "submit"
    fun setViewModel(activityViewModel: MainActivityViewModel){
        this.activityMainViewModel = activityViewModel
    }
    private fun nextFragment(){
        activityMainViewModel.nextFragment()
    }
    private fun previousFragment(){
        activityMainViewModel.previousFragment()
    }
    fun nextPreviousButtonsFormatting(bottomNavLayout: LinearLayout, nextButton: Button, previousButton: Button){
        nextButton.setOnClickListener {
            nextFragment()
        }
        if (hasPreviousFragment.not()){
            bottomNavLayout.removeView(previousButton)
        }
        nextButton.text=textForNextButton
        nextButton.setOnClickListener {
            nextFragment()
        }
        previousButton.setOnClickListener {
            previousFragment()
        }
    }
}