package com.example.registrationform.registration

import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.registrationform.RegistrationActivityViewModel

abstract class RegistrationFragment : Fragment() {
    //all registration fragments are only for main activity
    //every variable will be assigned by main activity view model
    //all functions will be used by child fragments
    abstract fun getFragmentName():String
    private lateinit var activityRegistrationViewModel: RegistrationActivityViewModel
    var hasNextFragment=true
    var hasPreviousFragment=true
    var textForNextButton = "submit"
    fun setViewModel(activityViewModel: RegistrationActivityViewModel){
        this.activityRegistrationViewModel = activityViewModel
    }
    private fun nextFragment(){
        activityRegistrationViewModel.nextFragment()
    }
    private fun previousFragment(){
        activityRegistrationViewModel.previousFragment()
    }
    protected fun nextPreviousButtonsFormatting(bottomNavLayout: LinearLayout, nextButton: Button, previousButton: Button,onNextButtonClick:()->Boolean,onPreviousButtonClick:()->Boolean){
        if (hasPreviousFragment.not()){
            bottomNavLayout.removeView(previousButton)
        }
        nextButton.text=textForNextButton
        nextButton.setOnClickListener {
            if (onNextButtonClick()) nextFragment()
        }
        previousButton.setOnClickListener {
            if (onPreviousButtonClick()) previousFragment()
        }
    }
    protected fun <T> setData(obj:T){
        activityRegistrationViewModel.setData(obj)
    }
}