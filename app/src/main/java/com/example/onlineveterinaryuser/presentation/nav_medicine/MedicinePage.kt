package com.example.onlineveterinaryuser.presentation.nav_medicine

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageMedicineBinding
import com.example.onlineveterinaryuser.presentation.activity.model.Account
import com.example.onlineveterinaryuser.presentation.nav_medicine.adapters.MedicineRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_medicine.models.Medicines
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MedicinePage:Fragment(R.layout.page_medicine) {

    private val binding by viewBinding(PageMedicineBinding::bind)
    private lateinit var medicineRvAdapter : MedicineRvAdapter
    private lateinit var medicinesList : ArrayList<Medicines>
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var reference : DatabaseReference


    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("doctors")
        medicinesList = ArrayList()

        medicineRvAdapter = MedicineRvAdapter(object:MedicineRvAdapter.OnItemTouchClickListener {
            override fun onItem(medicines : Medicines) {
                findNavController().navigate(
                    MedicinePageDirections.actionMedicinePageToInfoMedicineScreen(
                        medicines
                    )
                )
            }
        })

        reference.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                try {
                    val children = snapshot.children
                    for (child in children) {
                        val doctor = child.getValue(Account::class.java)

                        reference.child("${doctor?.uid}/myMedicine")
                            .addValueEventListener(object:ValueEventListener {
                                override fun onDataChange(snapshot : DataSnapshot) {
                                    try {
                                        medicinesList.clear()
                                        val children1 = snapshot.children
                                        for (child1 in children1) {
                                            val medicine = child1.getValue(Medicines::class.java)
                                            if (medicine != null) {
                                                medicinesList.add(medicine)
                                            }
                                        }

                                        medicineRvAdapter.mySubmitList(medicinesList)
                                        rvMedicine.apply {
                                            setHasFixedSize(true)
                                            adapter = medicineRvAdapter
                                        }
                                        medicineRvAdapter.notifyDataSetChanged()

                                    } catch (e : Exception) {
                                        showToast(e.message.toString())
                                    }
                                }

                                override fun onCancelled(error : DatabaseError) {
                                    showToast(error.message)

                                }
                            })
                    }

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