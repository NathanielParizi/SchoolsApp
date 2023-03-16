package com.example.schoolsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolsapp.R
import com.example.schoolsapp.databinding.FragmentSchoolListBinding
import com.example.schoolsapp.model.SatResponse

class SchoolListFragment : Fragment() {

    private val binding by lazy {
        FragmentSchoolListBinding.inflate(layoutInflater)
    }

    private val viewModel: SchoolViewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
    }

    private val schoolAdapter by lazy {
        SchoolAdapter {
            viewModel.selectedSchool = it

            val fragment = SchoolProfileFragment.newInstance()
            parentFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container, fragment, "detail")
                .addToBackStack(null)
                .commit()
        }
    }

    private val progressBar: ProgressBar? = null

    private var satScores: SatResponse? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = schoolAdapter
        }

        val progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE

        setupObserver()

        return binding.root
    }

    private fun setupObserver() {
        viewModel.school.observe(viewLifecycleOwner) {
            schoolAdapter.updateData(it)
            // Hide loading spinner
            progressBar?.visibility = View.GONE
        }
        viewModel.satScore.observe(viewLifecycleOwner) {
            satScores = it
        }
    }

    companion object {
        fun newInstance() = SchoolListFragment()
    }
}