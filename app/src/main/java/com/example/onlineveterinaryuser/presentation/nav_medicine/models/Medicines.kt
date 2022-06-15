package com.example.onlineveterinaryuser.presentation.nav_medicine.models

import java.io.Serializable

data class Medicines(
    val id:Int,
    val name : String ,
    val description : String ,
    val price : String ,
    val doctorsName : String ,
    val imageUrl : Int
):Serializable