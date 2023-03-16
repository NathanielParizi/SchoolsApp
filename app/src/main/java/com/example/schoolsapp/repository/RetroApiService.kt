package com.example.schoolsapp.repository


import com.example.schoolsapp.model.SatResponse
import com.example.schoolsapp.model.SchoolResponse
import retrofit2.http.GET
import javax.inject.Inject


interface RetroApiService {

    @GET("resource/s3k6-pzi2.json")
    suspend fun fetchSchool(): SchoolResponse

    @GET("resource/f9bf-2cp4.json")
    suspend fun fetchSatScores(): SatResponse

}


class RetroAPI @Inject constructor(private val retroAPIService: RetroApiService) {
    suspend fun fetchSchool(): SchoolResponse {
        return retroAPIService.fetchSchool()
    }

    suspend fun fetchSatScores(): SatResponse {
        return retroAPIService.fetchSatScores()
    }
}

