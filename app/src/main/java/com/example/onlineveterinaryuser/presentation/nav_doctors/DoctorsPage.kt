package com.example.onlineveterinaryuser.presentation.nav_doctors

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageDoctorsBinding
import com.example.onlineveterinaryuser.presentation.activity.ChatActivity
import com.example.onlineveterinaryuser.presentation.activity.model.Account
import com.example.onlineveterinaryuser.presentation.nav_doctors.adapters.DoctorsRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Doctor
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
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
                findNavController().navigate(DoctorsPageDirections.actionDoctorsPageToInfoDoctorScreen())
            }

            override fun onMessageClick(doctor : Doctor) {

                val intent = Intent(requireActivity() , ChatActivity::class.java)
                intent.putExtra("doctor" , doctor)
                startActivity(intent)


            }
        })

        reference.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                try {
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
                } catch (e : Exception) {
                    showToast(e.message.toString())
                }
            }

            override fun onCancelled(error : DatabaseError) {
                showToast(error.message)
            }
        })


    }

}