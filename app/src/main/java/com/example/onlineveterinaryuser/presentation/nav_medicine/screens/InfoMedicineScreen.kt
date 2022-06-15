package com.example.onlineveterinaryuser.presentation.nav_medicine.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenInfoMedicineBinding
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast

class InfoMedicineScreen:Fragment(R.layout.screen_info_medicine) {

    private val binding by viewBinding(ScreenInfoMedicineBinding::bind)
    private val args : InfoMedicineScreenArgs by navArgs()

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        val toolbar = findRootView(requireActivity()).findViewById(R.id.main_toolbar) as Toolbar
        toolbar.title = args.medicine?.name
        imgMedicineInfoImage.setImageResource(args.medicine!!.imageUrl)
        tvMedicineInfoDescription.text = args.medicine?.description
        tvMedicinesInfoDoctorsName.text = args.medicine?.doctorsName
        tvMedicineInfoPrise.text = args.medicine?.price

        btnOrder.setOnClickListener {
            showToast("Order")
        }

    }


}