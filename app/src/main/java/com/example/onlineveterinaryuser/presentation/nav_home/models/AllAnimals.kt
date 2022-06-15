package com.example.onlineveterinaryuser.presentation.nav_home.models

import java.io.Serializable

data class AllAnimals(
    val id : Int,
    val type : String ,
    val animalsList : ArrayList<Animal>
)

data class Animal(
    val id : Int ,
    val name : String ,
    val img : Int ,
    val data : String
):Serializable