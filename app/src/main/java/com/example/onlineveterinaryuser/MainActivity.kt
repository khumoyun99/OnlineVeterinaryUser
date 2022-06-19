package com.example.onlineveterinaryuser

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.onlineveterinaryuser.databinding.ActivityMainBinding
import com.example.onlineveterinaryuser.utils.UserBottomBackStackController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable


class MainActivity:AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration : AppBarConfiguration
    private var currentNavController : LiveData<NavController>? = null

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setBackgroundDrawableResource(R.color.white)



        setSupportActionBar(binding.mainToolbar)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        binding.apply {
            val materialShapeDrawable = appBarLayout.background as MaterialShapeDrawable
            materialShapeDrawable.shapeAppearanceModel =
                materialShapeDrawable.shapeAppearanceModel.toBuilder().setBottomRightCorner(
                    CornerFamily.ROUNDED , 30F
                ).setBottomLeftCorner(
                    CornerFamily.ROUNDED , 30F
                ).build()
        }
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false


        val bottomAppBarBackground : MaterialShapeDrawable =
            bottomAppBar.background as MaterialShapeDrawable


        bottomAppBarBackground.shapeAppearanceModel =
            bottomAppBarBackground.shapeAppearanceModel.toBuilder().setTopLeftCornerSize(
                30F
            ).setTopRightCornerSize(
                30F
            ).build()

        binding.fab.setOnClickListener {
            bottomNavigationView.menu.getItem(2).isEnabled = true
            bottomNavigationView.selectedItemId = R.id.nav_doctors_graph
        }


        val navGraphsIds = listOf(
            R.navigation.nav_home_graph ,
            R.navigation.nav_animals_graph ,
            R.navigation.nav_doctors_graph ,
            R.navigation.nav_medicine_graph ,
            R.navigation.nav_profile_graph
        )

        val userBottomBackStackController = UserBottomBackStackController()
        val controller = userBottomBackStackController.setupWithNavController(
            bottomNavigationView ,
            navGraphsIds ,
            supportFragmentManager ,
            R.id.nav_host_container_fragment
        )

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homePage ,
                R.id.animalsPage ,
                R.id.doctorsPage ,
                R.id.medicinePage ,
                R.id.profilePage
            )
        )

        controller.observe(this , Observer { navController ->
            binding.mainToolbar.setupWithNavController(navController)
            setupActionBarWithNavController(navController , appBarConfiguration)
        })
        currentNavController = controller

    }

    override fun onSupportNavigateUp() : Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

}