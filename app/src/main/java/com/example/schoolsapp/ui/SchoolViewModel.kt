package com.example.schoolsapp.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolsapp.model.SatResponse
import com.example.schoolsapp.model.SatResponseItem
import com.example.schoolsapp.model.SchoolResponse
import com.example.schoolsapp.model.SchoolResponseItem
import com.example.schoolsapp.repository.SchoolRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(private val schoolRepo: SchoolRepositoryImp) :
    ViewModel() {

    private val _school = MutableLiveData<SchoolResponse>()
    val school: MutableLiveData<SchoolResponse> get() = _school
    private val _satScores = MutableLiveData<SatResponse>()
    val satScore: MutableLiveData<SatResponse> get() = _satScores

    private val _errorMessage = MutableLiveData<String>()

    var selectedSchool: SchoolResponseItem? = null

    init {
        Log.d("GOLDTAG", "init")
        fetchSchool()
        fetchSatScores()
    }

    fun fetchSchool() {
        Log.d("GOLDTAG", "fetchSchool")


        viewModelScope.launch {
            try {
                val response = schoolRepo.fetchSchool().let {
                    school.postValue(it)
                }
                Result.success(response)
            } catch (e: Exception) {
                _errorMessage.postValue("Error: ${e.message}")
                Result.failure(e)
            }
        }
    }

    fun fetchSatScores() {
        Log.d("GOLDTAG", "fetchSatScores:")


        viewModelScope.launch {
            Log.d("GOLDTAG", "fetchSatScores:2")

            try {
                val response = schoolRepo.fetchSatScores().let {
                    Log.d("GOLDTAG", "fetchSatScores: ${it}")
                    satScore.postValue(it)
                }
                Result.success(response)

            } catch (e: Exception) {
                _errorMessage.postValue("Error: ${e.message}")
                Result.failure(e)
            }
        }
    }


}