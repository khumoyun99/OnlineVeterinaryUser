package com.example.onlineveterinaryuser.presentation.nav_login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.databinding.ScreenAuthBinding
import com.example.onlineveterinaryuser.utils.scope

class AuthScreen:Fragment() {

    private val binding by viewBinding(ScreenAuthBinding::bind)

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)
    }
}