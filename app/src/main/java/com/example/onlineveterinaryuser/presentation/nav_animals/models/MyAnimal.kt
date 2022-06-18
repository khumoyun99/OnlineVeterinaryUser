package com.example.onlineveterinaryuser.presentation.nav_animals.models

import java.io.Serializable

class MyAnimal:Serializable {
    var uid : String? = null
    var animalType : String? = null
    var name : String? = null
    var age : String? = null
    var color : String? = null
    var type : String? = null
    var gender : String? = null
    var weight : String? = null
    var widHeight : String? = null
    var photoUrl : String? = null
    var additional : String? = null


    constructor(
        uid : String? ,
        animalType : String? ,
        name : String? ,
        age : String? ,
        color : String? ,
        type : String? ,
        gender : String? ,
        weight : String? ,
        widHeight : String? ,
        photoUrl : String? ,
        additional : String?
    ) {
        this.uid = uid
        this.animalType = animalType
        this.name = name
        this.age = age
        this.color = color
        this.type = type
        this.gender = gender
        this.weight = weight
        this.widHeight = widHeight
        this.photoUrl = photoUrl
        this.additional = additional
    }

    constructor()
}
