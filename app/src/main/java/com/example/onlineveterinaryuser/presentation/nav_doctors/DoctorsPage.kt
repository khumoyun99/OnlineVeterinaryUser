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
import com.example.onlineveterinaryuser.presentation.activity.model.Account
import com.example.onlineveterinaryuser.presentation.nav_doctors.adapters.DoctorsRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Doctor
import com.example.onlineveterinaryuser.utils.gone
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
import com.example.onlineveterinaryuser.utils.visible
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DoctorsPage:Fragment(R.layout.page_doctors) {

    private val binding by viewBinding(PageDoctorsBinding::bind)
    private lateinit var doctorsRvAdapter : DoctorsRvAdapter
    private lateinit var doctorsList : ArrayList<Doctor>
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var reference : DatabaseReference

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        activity?.window?.setBackgroundDrawableResource(R.color.white)
        doctorsList = ArrayList()
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("doctors")

        doctorsRvAdapter = DoctorsRvAdapter(object:DoctorsRvAdapter.OnDoctorsTouchListener {
            override fun onClick(doctor : Doctor) {
                val bottomNav =
                    findRootView(requireActivity()).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomNav.gone()

                val bottomAppBar =
                    findRootView(requireActivity()).findViewById<BottomAppBar>(R.id.bottomAppBar)
                bottomAppBar.gone()

                val fab =
                    findRootView(requireActivity()).findViewById<FloatingActionButton>(R.id.fab)
                fab.gone()

                findNavController().navigate(DoctorsPageDirections.actionDoctorsPageToInfoDoctorScreen())
            }

            override fun onMessageClick(doctor : Doctor) {
                val bottomNav =
                    findRootView(requireActivity()).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomNav.gone()

                val bottomAppBar =
                    findRootView(requireActivity()).findViewById<BottomAppBar>(R.id.bottomAppBar)
                bottomAppBar.gone()

                val fab =
                    findRootView(requireActivity()).findViewById<FloatingActionButton>(R.id.fab)
                fab.gone()


                findNavController().navigate(DoctorsPageDirections.actionDoctorsPageToMessageDoctorScreen())

                val toolbar =
                    findRootView(requireActivity()).findViewById<AppBarLayout>(R.id.main_toolbar) as Toolbar
                toolbar.title = "Doctors name"

            }
        })

        reference.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                doctorsList.clear()
                val children = snapshot.children
                for (child in children) {
                    val account = child.getValue(Account::class.java)
                    doctorsList.add(
                        Doctor(
                            id = account?.uid.toString() ,
                            name = account?.displayName.toString() ,
                            image = account?.photoUrl.toString() ,
                            rating = 4.5f ,
                            profession = getString(R.string.veterinary)
                        )
                    )
                }
                doctorsRvAdapter.muSubmitList(doctorsList)
                rvDoctors.setHasFixedSize(true)
                rvDoctors.adapter = doctorsRvAdapter
                doctorsRvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error : DatabaseError) {
                showToast(error.message)
            }
        })


    }

    override fun onResume() {
        super.onResume()
        val bottomNav =
            findRootView(requireActivity()).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.visible()

        val bottomAppBar =
            findRootView(requireActivity()).findViewById<BottomAppBar>(R.id.bottomAppBar)
        bottomAppBar.visible()

        val fab = findRootView(requireActivity()).findViewById<FloatingActionButton>(R.id.fab)
        fab.visible()

    }
}