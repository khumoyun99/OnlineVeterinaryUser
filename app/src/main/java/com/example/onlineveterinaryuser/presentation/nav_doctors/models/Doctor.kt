package com.example.onlineveterinaryuser.presentation.nav_doctors.models

import java.io.Serializable

data class Doctor(
    val id : Int ,
    val name : String ,
    val image : Int,
    val rating:Float,
    val profession:String
):Serializable