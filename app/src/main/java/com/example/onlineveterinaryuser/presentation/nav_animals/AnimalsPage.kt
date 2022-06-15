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

class AnimalsPage:Fragment(R.layout.page_animals) {

    private val binding by viewBinding(PageAnimalsBinding::bind)
    private lateinit var myAnimalsRvAdapter : MyAnimalsRvAdapter
    private lateinit var animalsList : ArrayList<MyAnimal>

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)
        setHasOptionsMenu(true)

        myAnimalsRvAdapter = MyAnimalsRvAdapter(object:MyAnimalsRvAdapter.OnMyAnimalsTouchListener {
            override fun onItemClick(animal : MyAnimal) {
                findNavController().navigate(AnimalsPageDirections.actionAnimalsPageToEditAnimalsScreen())
            }
        })

//        fab.setOnClickListener {
//            findNavController().navigate(AnimalsPageDirections.actionAnimalsPageToAddAnimalScreen())
//        }

        loadData()

        rvMyAnimals.setHasFixedSize(true)
        myAnimalsRvAdapter.mySubmitList(animalsList)
        rvMyAnimals.adapter = myAnimalsRvAdapter


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

    private fun loadData() {
        animalsList = ArrayList()

        for (i in 0 until 10) {
            animalsList.add(MyAnimal(i , "Tuzik" , R.drawable.only_dog))
        }
    }

}