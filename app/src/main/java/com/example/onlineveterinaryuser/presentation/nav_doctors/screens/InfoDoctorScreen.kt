package com.example.onlineveterinaryuser.presentation.nav_doctors.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenInfoDoctorBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.adapters.EducationRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_doctors.adapters.JobExperienceRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Education
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Work
import com.example.onlineveterinaryuser.utils.scope
import com.google.android.material.bottomnavigation.BottomNavigationView

class InfoDoctorScreen:Fragment(R.layout.screen_info_doctor) {

    private val binding by viewBinding(ScreenInfoDoctorBinding::bind)
    private lateinit var jobExperienceRvAdapter : JobExperienceRvAdapter
    private lateinit var educationRvAdapter : EducationRvAdapter
    private lateinit var workList : ArrayList<Work>
    private lateinit var educationList : ArrayList<Education>

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        loadData()

        jobExperienceRvAdapter = JobExperienceRvAdapter()
        jobExperienceRvAdapter.mySubmitList(workList)
        rvJobExperience.apply {
            adapter = jobExperienceRvAdapter
            setHasFixedSize(true)
        }


        educationRvAdapter = EducationRvAdapter()
        educationRvAdapter.mySubmitList(educationList)
        rvEducation.apply {
            adapter = educationRvAdapter
            setHasFixedSize(true)
        }


    }

    private fun loadData() {
        workList = ArrayList()
        educationList = ArrayList()
        for (i in 0 until 1) {
            workList.add(
                Work(
                    id = i ,
                    workPlace = "Yunusobod veterinariya markazi" ,
                    profession = "Veterinar * To'liq stavka" ,
                    periodTime = "Nov 2020 - Mar 2022" ,
                    workingPlaceAddress = "Yunusobod tumani Amir Temur ko'chasi 10, O'zbekiston"
                )
            )
            educationList.add(
                Education(
                    id = i ,
                    namePlaceOfStudy = "Samarqand veterinariya meditsina instituti" ,
                    degree = "Bakalavr" ,
                    period = "Nov 2000 - Mar 2004" ,
                    address = "Samarqand veterinariya meditsina instituti"
                )
            )
        }

    }

    override fun onResume() {
        super.onResume()
//        val bottomNav =
//            findRootView(requireActivity()).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        bottomNav.visibility = View.GONE
    }

}