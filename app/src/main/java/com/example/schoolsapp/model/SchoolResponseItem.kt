package com.example.schoolsapp.model


import com.google.gson.annotations.SerializedName

data class SchoolResponseItem(
    @SerializedName("bin")
    val bin: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("state_code")
    val stateCode: String,
    @SerializedName("total_students")
    val totalStudents: String,
    @SerializedName("transfer")
    val transfer: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("zip")
    val zip: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("school_email")
    val schoolEmail: String,
    @SerializedName("school_sports")
    val schoolSports: String
)