package com.example.onlineveterinaryuser.presentation.nav_doctors.models

import java.io.Serializable

data class Doctor(
    val id : String ,
    val name : String ,
    val image : String,
    val rating:Float,
    val profession:String
):Serializable