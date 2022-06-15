package com.example.onlineveterinaryuser.presentation.nav_doctors

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageDoctorsBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.adapters.DoctorsRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Doctor
import com.example.onlineveterinaryuser.utils.scope
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class DoctorsPage:Fragment(R.layout.page_doctors) {

    private val binding by viewBinding(PageDoctorsBinding::bind)
    private lateinit var doctorsRvAdapter : DoctorsRvAdapter
    private lateinit var doctorsList : ArrayList<Doctor>

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        activity?.window?.setBackgroundDrawableResource(R.color.white)

        loadData()
        doctorsRvAdapter = DoctorsRvAdapter(object:DoctorsRvAdapter.OnDoctorsTouchListener {
            override fun onClick(doctor : Doctor) {
                findNavController().navigate(DoctorsPageDirections.actionDoctorsPageToInfoDoctorScreen())
            }

            override fun onMessageClick(doctor : Doctor) {
                val bottomNav =
                    findRootView(requireActivity()).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomNav.visibility = View.GONE

                findNavController().navigate(DoctorsPageDirections.actionDoctorsPageToMessageDoctorScreen())

                val toolbar =
                    findRootView(requireActivity()).findViewById<AppBarLayout>(R.id.main_toolbar) as Toolbar
                toolbar.title = "Doctors name"

            }
        })

        rvDoctors.setHasFixedSize(true)
        doctorsRvAdapter.muSubmitList(doctorsList)
        rvDoctors.adapter = doctorsRvAdapter

    }

    private fun loadData() {
        doctorsList = ArrayList()

        for (i in 0 until 10) {
            doctorsList.add(
                Doctor(
                    i ,
                    name = "Malika" ,
                    image = R.drawable.doctors1 ,
                    rating = 4.5f ,
                    profession = "Veterinary"
                )
            )
        }

    }

    override fun onResume() {
        super.onResume()
        val bottomNav =
            findRootView(requireActivity()).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.visibility = View.VISIBLE
    }
}