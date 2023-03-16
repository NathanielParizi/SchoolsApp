package com.example.schoolsapp.repository

import com.example.schoolsapp.model.SatResponse
import com.example.schoolsapp.model.SchoolResponse
import javax.inject.Inject

interface SchoolRepository {
    suspend fun fetchSchool(): SchoolResponse
    suspend fun fetchSatScores(): SatResponse
}

class SchoolRepositoryImp @Inject constructor(retroAPIService: RetroApiService) :
    SchoolRepository {
    private val retroAPI: RetroAPI = RetroAPI(retroAPIService)

    override suspend fun fetchSchool(): SchoolResponse {
        return retroAPI.fetchSchool()

    }

    override suspend fun fetchSatScores(): SatResponse {
        return retroAPI.fetchSatScores()
    }

}
