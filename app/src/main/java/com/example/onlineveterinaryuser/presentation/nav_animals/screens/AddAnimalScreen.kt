package com.example.onlineveterinaryuser.presentation.nav_animals.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenAddAnimalBinding
import com.example.onlineveterinaryuser.presentation.nav_animals.models.MyAnimal
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddAnimalScreen:Fragment(R.layout.screen_add_animal) {

    private val binding by viewBinding(ScreenAddAnimalBinding::bind)
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var reference : DatabaseReference

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference =
            firebaseDatabase.getReference("users/${firebaseAuth.currentUser?.uid}/myAnimals")
        val key = reference.push().key


        btnAddAnimal.setOnClickListener {
            val type = etAddType.text.toString()
            val name = etAddName.text.toString()
            val age = etAddAge.text.toString()
            val color = etAddColor.text.toString()
            val animalType = etAddAnimalType.text.toString()
            val gender = etAddGender.text.toString()
            val weight = etAddAnimalWeight.text.toString()
            val widHeight = etAddAnimalWidthHeight.text.toString()
            val photoUrl = "photoUrl"
            val additional = etAddAdditional.text.toString()

            if (type.isNotEmpty() && name.isNotEmpty() && age.isNotEmpty() && color.isNotEmpty()
                && animalType.isNotEmpty() && gender.isNotEmpty() && weight.isNotEmpty()
                && widHeight.isNotEmpty() && photoUrl.isNotEmpty() && additional.isNotEmpty()
            ) {
                val myAnimal = MyAnimal(
                    key ,
                    type ,
                    name ,
                    age ,
                    color ,
                    type ,
                    gender ,
                    weight ,
                    widHeight ,
                    photoUrl ,
                    additional
                )
//                val myAnimalsHashMap : HashMap<String , MyAnimal> = HashMap()
//                myAnimalsHashMap[key ?: ""] = myAnimal
                reference.child(key?:"").setValue(myAnimal)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("Sizning uy hayvoningiz qo'shildi")
                            etAddType.text?.clear()
                            etAddName.text?.clear()
                            etAddAge.text?.clear()
                            etAddColor.text?.clear()
                            etAddAnimalType.text?.clear()
                            etAddGender.text?.clear()
                            etAddAnimalWeight.text?.clear()
                            etAddAnimalWidthHeight.text?.clear()
                            etAddAdditional.text?.clear()
                        } else {
                            showToast(it.exception?.message.toString())
                        }
                    }

            } else {
                showToast("Bo'sh kataklarni to'ldiring")
            }
        }

    }
}