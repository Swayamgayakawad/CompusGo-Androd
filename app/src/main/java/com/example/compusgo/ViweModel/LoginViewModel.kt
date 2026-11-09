
package ViweModel

import RoomDB.StudentDB
import RoomDB.StudentRepository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import java.util.Calendar

class LoginViewModel(private val repository: StudentRepository) : ViewModel() {

    // Student information

    var year by mutableStateOf(0)
        private set

    var sem by mutableStateOf(0)
        private set

    var branch by mutableStateOf("")
        private set

    var serialNumber by mutableStateOf(0)
        private set

    var USN by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var ConfirmPassword by mutableStateOf("")
        private set

    var Name by mutableStateOf("")
        private set

    var Email by mutableStateOf("")
        private set

    var Phone by mutableStateOf("")
        private set

    var registrationStatus by mutableStateOf("")
        private set

    var loginStatus by mutableStateOf("")
        private set


    // Input functions

    fun onUSNChange(value: String) {
        USN = value.trim().uppercase()
        extractUSN(USN)
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onConfirmPasswordChange(value: String) {
        ConfirmPassword = value
    }

    fun onNameChange(value: String) {
        Name = value
    }

    fun onEmailChange(value: String) {
        Email = value
    }

    fun onPhoneChange(value: String) {
        Phone = value
    }


    // Password validation

    fun checkRegistrationPassword(): Boolean {

        return if (
            password.isNotBlank() &&
            password == ConfirmPassword
        ) {
            registrationStatus = ""
            true

        } else {

            registrationStatus =
                if (password.isBlank()) {
                    "Password cannot be empty"
                } else {
                    "Passwords do not match"
                }

            false
        }
    }


    // USN extraction

    fun extractUSN(usn: String): Boolean {

        val pattern =
            Regex("""^\d[A-Z]{2}(\d{2})([A-Z]{2})(\d{3})$""")

        val match = pattern.matchEntire(usn.trim().uppercase())

        if (match == null) {
            year = 0
            branch = ""
            serialNumber = 0
            sem = 0
            return false
        }

        year = match.groupValues[1].toInt()
        branch = match.groupValues[2]
        serialNumber = match.groupValues[3].toInt()

        sem = calculateSemester(year)

        return true
    }


    // Calculate semester

    private fun calculateSemester(admissionYear: Int): Int {

        val calendar = Calendar.getInstance()

        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1

        val fullAdmissionYear = 2000 + admissionYear

        val academicYear = if (currentMonth >= 8) {
            currentYear
        } else {
            currentYear - 1
        }

        val yearDifference = academicYear - fullAdmissionYear

        if (yearDifference < 0) return 0

        val semester = if (currentMonth >= 8) {
            yearDifference * 2 + 1
        } else {
            yearDifference * 2 + 2
        }

        return if (semester in 1..8) semester else 0
    }


    // Registration


    fun registerUser(onSuccess: () -> Unit = {}) {

        if (!checkRegistrationPassword()) return

        if (
            USN.isBlank() ||
            Name.isBlank() ||
            Phone.isBlank()
        ) {
            registrationStatus = "USN, Name and Phone cannot be empty"
            return
        }

        if (!extractUSN(USN)) {
            registrationStatus = "Invalid USN format"
            return
        }

        if (sem == 0) {
            registrationStatus = "Invalid academic semester"
            return
        }

        viewModelScope.launch {
            try {

                // Check whether USN already exists
                val existingStudent = repository.getStudentByUSN(USN)

                if (existingStudent != null) {
                    registrationStatus = "USN already registered"
                    return@launch
                }

                // Create student object
                val newStudent = StudentDB(
                    USN = USN,
                    Branch = branch,
                    Year = year,
                    seralNo = serialNumber,
                    Semester = sem,
                    Name = Name,
                    Phone = Phone,
                    password = password
                )

                // Save student in Room
                repository.insertStudent(newStudent)

                registrationStatus = "Registration successful!"
                Status.isLoggedIn = true

                onSuccess()

            } catch (e: Exception) {
                registrationStatus = "Registration failed: ${e.message}"
            }
        }
    }


    // Login


    fun loginUser(onSuccess: () -> Unit = {}) {

        if (USN.isBlank() || password.isBlank()) {
            loginStatus = "USN and Password cannot be empty"
            return
        }

        viewModelScope.launch {
            try {

                // Find student using USN
                val user = repository.getStudentByUSN(USN)

                if (user == null) {

                    loginStatus = "User not found"

                } else if (user.password == password) {

                    loginStatus = "Login successful"
                    Status.isLoggedIn = true

                    onSuccess()

                } else {

                    loginStatus = "Incorrect password"
                }

            } catch (e: Exception) {
                loginStatus = "Login failed: ${e.message}"
            }
        }
    }
    fun checkUser(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val user = repository.checkUser()

                Status.isLoggedIn = user != null

                onResult(user != null)

            } catch (e: Exception) {
                Status.isLoggedIn = false
                onResult(false)
            }
        }
    }
}
