package com.example.onlineveterinaryuser.presentation.nav_home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageHomeBinding
import com.example.onlineveterinaryuser.presentation.nav_home.adapters.AllAnimalsRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_home.adapters.VeterinaryImagesVpAdapter
import com.example.onlineveterinaryuser.presentation.nav_home.models.AllAnimals
import com.example.onlineveterinaryuser.presentation.nav_home.models.Animal
import com.example.onlineveterinaryuser.utils.scope

class HomePage:Fragment(R.layout.page_home) {

    private val binding by viewBinding(PageHomeBinding::bind)
    private lateinit var veterinaryImagesVpAdapter : VeterinaryImagesVpAdapter
    private lateinit var allAnimalsRvAdapter : AllAnimalsRvAdapter
    private lateinit var allAnimalsList : ArrayList<AllAnimals>

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        val compositePageTrans = CompositePageTransformer()
        veterinaryImagesVpAdapter = VeterinaryImagesVpAdapter()
        vpVeterinaryImages.apply {
            adapter = veterinaryImagesVpAdapter
            setPadding(80 , 0 , 80 , 0)
            setBackgroundColor(Color.TRANSPARENT)
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false

            compositePageTrans.addTransformer(MarginPageTransformer(15))
            compositePageTrans.addTransformer { page , pos ->
                val r : Float = 1 - kotlin.math.abs(pos)
                page.scaleY = 0.85f + r * 0.15f
            }
            setPageTransformer(compositePageTrans)
            setCurrentItem(3 , false)
        }
        loadData()
        allAnimalsRvAdapter =
            AllAnimalsRvAdapter(object:AllAnimalsRvAdapter.OnAllAnimalsClickListener {
                override fun onItemClick(animal : Animal) {
                    findNavController().navigate(HomePageDirections.actionHomePageToAboutScreen(animal))
                }
            })
        allAnimalsRvAdapter.mySubmitList(allAnimalsList)
        rvAllAnimals.adapter = allAnimalsRvAdapter

    }

    private fun loadData() {
        allAnimalsList = ArrayList()
        val dogsList = arrayListOf<Animal>()
        val catsList = arrayListOf<Animal>()

        val dogsImg = arrayListOf(
            R.drawable.img_dog_ovcharka ,
            R.drawable.img_dog_american_bulldog ,
            R.drawable.img_dog_berger_picard ,
            R.drawable.img_dog_yorkshire_terrier ,
            R.drawable.img_dog_american_eskimo
        )
        val catsImg = arrayListOf(
            R.drawable.img_cat_american_shorthair ,
            R.drawable.img_cat_exotic_shorthair ,
            R.drawable.img_cat_maine_coon ,
            R.drawable.img_cat_ragdoll ,
            R.drawable.img_cat_mixed_breed
        )


        for (i in 0 until 5) {
            dogsList.add(Animal(i , "dog" , dogsImg[i] , "data"))
            catsList.add(Animal(i , "cat" , catsImg[i] , "data"))
        }

        allAnimalsList.add(AllAnimals(0 , "Dogs" , dogsList))
        allAnimalsList.add(AllAnimals(0 , "Cats" , catsList))
    }


}