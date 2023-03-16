package com.example.schoolsapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.schoolsapp.databinding.FragmentSchoolProfileBinding
import com.example.schoolsapp.model.SatResponse
import com.example.schoolsapp.model.SatResponseItem
import com.example.schoolsapp.model.SchoolResponseItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolProfileFragment : Fragment() {

    private lateinit var school: SchoolResponseItem
    private lateinit var sat: SatResponse

    private var goldSatScore: SatResponseItem? = null


    private val binding by lazy {
        FragmentSchoolProfileBinding.inflate(layoutInflater)
    }

    private val viewModel: SchoolViewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        viewModel.selectedSchool?.let {
            school = it
            binding.schoolDbn.text = "DBN ID:\t${it.dbn}"
            binding.schoolName.text = "Name:\t ${it.schoolName}"
            binding.schoolEmail.text = "Email:\t${it.schoolEmail}"
            binding.schoolAddress.text = "Address:\t${it.primaryAddressLine1}"
            binding.schoolCity.text = "City:\t${it.city}"
            binding.stateCode.text = "State:\t${it.stateCode}"
            binding.schoolSports.text = "Zip:\t${it.zip}"
            binding.startTime.text = "Start time:\t${it.startTime}"
            binding.totalStudents.text = "Total Students:\t${it.totalStudents}"

        } ?: run {
            AlertDialog.Builder(requireActivity())
                .setTitle("Error occurred")
                .setMessage("No school selected")
                .setNegativeButton("Dismiss") { dialog, _ ->
                    dialog.dismiss()
                }
        }

        viewModel.satScore.observe(viewLifecycleOwner) {

            val goldSatScore = it.find {
                getSatScoreIfDbnMatches(school, it)?.let { true } ?: false
            }

            if (goldSatScore != null) {
                // goldSatScore is not null, do something with it
                binding.satDbn.text = "DBN ID:\t${goldSatScore?.dbn}"
                binding.satSchoolName.text = "Name:\t ${goldSatScore?.schoolName}"
                binding.numOfSatTestTakers.text =
                    "Number of test takers:\t${goldSatScore.numOfSatTestTakers}"
                binding.satMathAvgScore.text =
                    "Average Math Scores:\t${goldSatScore.satMathAvgScore}"
                binding.satWritingAvgScore.text =
                    "Average Writing Scores:\t${goldSatScore.satWritingAvgScore}"
                binding.satCriticalReadingAvgScore.text =
                    "Average Reading Scores:\t${goldSatScore.satCriticalReadingAvgScore}"
            } else {
                // goldSatScore is null, handle the case where there is no match
                binding.satDbn.text = "Data not provided"
                binding.satSchoolName.text = ""
                binding.numOfSatTestTakers.text = ""
                binding.satMathAvgScore.text = ""
                binding.satWritingAvgScore.text = ""
                binding.satCriticalReadingAvgScore.text = ""
            }


        }

        return binding.root
    }

    fun getSatScoreIfDbnMatches(
        school: SchoolResponseItem,
        satScores: SatResponseItem
    ): SatResponseItem? {
        return if (school.dbn.toLowerCase() == satScores.dbn.toLowerCase()) {
            satScores
        } else {
            return null
        }
    }


    companion object {
        fun newInstance() = SchoolProfileFragment()
    }
}

