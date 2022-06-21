package com.example.onlineveterinaryuser.presentation.nav_animals

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageAnimalsBinding
import com.example.onlineveterinaryuser.presentation.nav_animals.adapters.MyAnimalsRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_animals.models.MyAnimal
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.lang.Exception

class AnimalsPage:Fragment(R.layout.page_animals) {

    private val binding by viewBinding(PageAnimalsBinding::bind)
    private lateinit var myAnimalsRvAdapter : MyAnimalsRvAdapter
    private lateinit var animalsList : ArrayList<MyAnimal>
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var reference : DatabaseReference

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)
        setHasOptionsMenu(true)

        animalsList = ArrayList()

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference =
            firebaseDatabase.getReference("users/${firebaseAuth.currentUser?.uid}/myAnimals")

        myAnimalsRvAdapter = MyAnimalsRvAdapter(object:MyAnimalsRvAdapter.OnMyAnimalsTouchListener {
            override fun onItemClick(animal : MyAnimal) {
                findNavController().navigate(
                    AnimalsPageDirections.actionAnimalsPageToEditAnimalsScreen(
                        animal
                    )
                )
            }
        })

        reference.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                try {
                    animalsList.clear()
                    val children = snapshot.children
                    for (child in children) {
                        val myAnimal = child.getValue(MyAnimal::class.java)
                        if (myAnimal != null) {
                            animalsList.add(myAnimal)
                        }
                    }
                    myAnimalsRvAdapter.mySubmitList(animalsList)
                    rvMyAnimals.setHasFixedSize(true)
                    rvMyAnimals.adapter = myAnimalsRvAdapter
                    myAnimalsRvAdapter.notifyDataSetChanged()
                } catch (e : Exception) {
                    showToast(e.message.toString())
                }
            }

            override fun onCancelled(error : DatabaseError) {
                showToast(error.message)
            }
        })

    }

    override fun onCreateOptionsMenu(menu : Menu , inflater : MenuInflater) {
        super.onCreateOptionsMenu(menu , inflater)
        inflater.inflate(R.menu.nav_animals_menu , menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        when (item.itemId) {
            R.id.addAnimals -> {
                findNavController().navigate(AnimalsPageDirections.actionAnimalsPageToAddAnimalScreen())
            }
        }
        return super.onOptionsItemSelected(item)
    }

}