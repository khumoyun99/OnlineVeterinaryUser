package com.example.onlineveterinaryuser.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.onlineveterinaryuser.R

class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onSupportNavigateUp() : Boolean {
        return findNavController(R.id.nav_login_fragment_container).navigateUp() || super.onSupportNavigateUp()
    }
}