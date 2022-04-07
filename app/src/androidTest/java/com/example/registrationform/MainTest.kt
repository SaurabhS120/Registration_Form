package com.example.registrationform

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun mainTest(){
        onView(withId(R.id.firstNameEditText)).perform(scrollTo()).perform(typeText("abc"))
        onView(withId(R.id.lastNameEditText)).perform(scrollTo()).perform(typeText("abc"))
        onView(withId(R.id.phoneNoEditText)).perform(scrollTo()).perform(typeText("1234567890"))
        onView(withId(R.id.emailEditText)).perform(scrollTo()).perform(typeText("abc@abc.com"))
        onView(withId(R.id.maleRadioButton)).perform(scrollTo()).perform(click())
        onView(withId(R.id.passwordEditText)).perform(scrollTo()).perform(typeText("aaaaaa1!"))
        onView(withId(R.id.confirmPasswordEditText)).perform(scrollTo()).perform(typeText("aaaaaa1!"))
        onView(withId(R.id.nextButton)).perform(scrollTo()).perform(click())

        onView(withId(R.id.education_spinner)).perform(scrollTo()).perform(click())
        onData(anything()).atPosition(1).perform(click())
        onView(withId(R.id.passingYearSpinner)).perform(scrollTo()).perform(click())
        onData(anything()).atPosition(1).perform(click())
        onView(withId(R.id.gradeEditText)).perform(scrollTo()).perform(typeText("a"))
        onView(withId(R.id.experienceEditText)).perform(scrollTo()).perform(typeText("1"))
        onView(withId(R.id.designationEditText)).perform(scrollTo()).perform(typeText("a"))
        onView(withId(R.id.domainEditText)).perform(scrollTo()).perform(typeText("a"))
        onView(withId(R.id.nextButton)).perform(scrollTo()).perform(click())

        onView(withId(R.id.address_edit_text)).perform(scrollTo()).perform(typeText("abc"))
        onView(withId(R.id.landmark_edit_text)).perform(scrollTo()).perform(typeText("abc"))
        onView(withId(R.id.city_edit_text)).perform(scrollTo()).perform(typeText("abc"))
        onView(withId(R.id.state_spinner)).perform(scrollTo()).perform(click())
        onData(anything()).atPosition(1).perform(click())
        onView(withId(R.id.pin_code_edit_text)).perform(scrollTo()).perform(typeText("111111"))
        onView(withId(R.id.nextButton)).perform(scrollTo()).perform(click())






    }
}