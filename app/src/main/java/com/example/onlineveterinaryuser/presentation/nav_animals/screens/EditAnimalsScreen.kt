package com.example.onlineveterinaryuser.presentation.nav_animals.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenEditAnimalsBinding
import com.example.onlineveterinaryuser.presentation.nav_animals.models.MyAnimal
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditAnimalsScreen:Fragment(R.layout.screen_edit_animals) {

    private val binding by viewBinding(ScreenEditAnimalsBinding::bind)
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var reference : DatabaseReference
    private val args : EditAnimalsScreenArgs by navArgs()

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference =
            firebaseDatabase.getReference("users/${firebaseAuth.currentUser?.uid}/myAnimals")

        loadData()
        btnEditSave.setOnClickListener {
            val type = etType.text.toString()
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val color = etColor.text.toString()
            val animalType = etAnimalType.text.toString()
            val gender = etGender.text.toString()
            val weight = etAnimalWeight.text.toString()
            val widHeight = etAnimalWidthHeight.text.toString()
            val photoUrl = "photoUrl"
            val additional = etAdditional.text.toString()

            if (type.isNotEmpty() && name.isNotEmpty() && age.isNotEmpty() && color.isNotEmpty()
                && animalType.isNotEmpty() && gender.isNotEmpty() && weight.isNotEmpty()
                && widHeight.isNotEmpty() && photoUrl.isNotEmpty() && additional.isNotEmpty()
            ) {
                val myAnimal = MyAnimal(
                    args.myAnimals.uid ,
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
                reference.child(args.myAnimals.uid ?: "").setValue(myAnimal)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("Ma'lumotlar o'zgartirildi")
                        }
                    }
            }
        }
    }

    private fun loadData() {
        binding.apply {
            etType.setText(args.myAnimals.type)
            etName.setText(args.myAnimals.name)
            etAge.setText(args.myAnimals.age)
            etColor.setText(args.myAnimals.color)
            etAnimalType.setText(args.myAnimals.animalType)
            etGender.setText(args.myAnimals.gender)
            etAnimalWeight.setText(args.myAnimals.weight)
            etAnimalWidthHeight.setText(args.myAnimals.widHeight)
            etAdditional.setText(args.myAnimals.additional)
        }
    }

}