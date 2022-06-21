package com.example.onlineveterinaryuser.presentation.nav_doctors.screens

import android.annotation.SuppressLint
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenMessageDoctorBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.adapters.UserDoctorRvMessageAdapter
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast

class MessageDoctorScreen:Fragment(R.layout.screen_message_doctor) {

    private val binding by viewBinding(ScreenMessageDoctorBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)


    }
}