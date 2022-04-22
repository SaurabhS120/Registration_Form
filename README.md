# Registration_Form

### In this project you can create users and display them in an recycerview and also you can see their details.

code - https://github.com/SaurabhS120/Registration_Form/tree/master/app/src/main

Description
------------------------------------------------
In this app you can register multiple users and see their details as per need. In MainActivityViewmodel we have list of multiple fragments which are handled dynamically and it is easy to add or remove fragments as per need. We are using Room database to store user details and LiveData to observe database records and user inputs.

Screenshots
--------------------------------------------------------------------------------------------------------------------------------------------
<p>
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/MainActivity.jpg" alt="Main Activity Screenshot" width="200px">
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/BasicDetailsFragment1.png" alt="Basic Details Fragment Screenshot" width="200px">
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/BasicDetailsFragment2.png" alt="Basic Details Fragment Screenshot" width="200px">
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/EducationDetailsFragment2.png" alt="Education Details Fragment Screenshot" width="200px">
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/AddressDatailsFragment.png" alt="Address Details Fragment Screenshot" width="200px">
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/UserDetailsActivity1.jpg" alt="User Details Activity Screenshot" width="200px">
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/UserDetailsActivity2.jpg" alt="User Details Activity Screenshot" width="200px">
<img src="https://github.com/SaurabhS120/Registration_Form/blob/master/Screenshots/UserDetailsActivity3.jpg" alt="User Details Activity Screenshot" width="200px">
</p>

Libraries Used
------------------------------------------------------------------------------------------------------------------------------------------
* Architecture
  -----------------------------------------------------------------------------------------------------------------------------------------   
  * **ViewModel** - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
  * **LiveData** - Build data objects that notify views when the underlying database changes.
  * **Lifecycles** - Create a UI that automatically responds to lifecycle events.
  * **Data Binding** - Library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
* Dependency Injection
  ------------------------------------------------------------------------------------------------------------------------------------------
  * **HILT** - Hilt is built on top of the popular DI library Dagger to benefit from the compile time correctness, runtime performance, scalability, and Android Studio support that Dagger provides. Recommended by Android.
* Database
  --------------------------------------
  * **Room** - Store offline database
* Gradle
  --------------------------------------
  * **Gradle** Kotlin DSL
* Test -
  ------------------------------------------
  An Android testing framework for unit and runtime UI tests. Given When Then — Our Testing Approach
  
  * Unit Tests (JUnit 5 via android-junit5)
  * UT Tests (Espresso)
