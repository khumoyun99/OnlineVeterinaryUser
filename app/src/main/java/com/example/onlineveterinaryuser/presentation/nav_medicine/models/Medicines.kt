package com.example.onlineveterinaryuser.presentation.nav_medicine.models

import java.io.Serializable

class Medicines:Serializable {
    var id : String? = null
    var name : String? = null
    var description : String? = null
    var price : Int? = 0
    var doctorsName : String? = null
    var imageUrl : String? = null

    constructor()

    constructor(
        id : String? ,
        name : String? ,
        description : String? ,
        price : Int? ,
        doctorsName : String? ,
        imageUrl : String?
    ) {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
        this.doctorsName = doctorsName
        this.imageUrl = imageUrl
    }

}