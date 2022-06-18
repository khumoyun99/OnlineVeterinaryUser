package com.example.onlineveterinaryuser.presentation.activity.model

import com.example.onlineveterinaryuser.presentation.nav_animals.models.MyAnimal
import java.io.Serializable

class Account:Serializable {
    var uid : String? = null
    var displayName : String? = null
    var email : String? = null
    var photoUrl : String? = null
    var phoneNumber : String? = null
    var myAnimals : HashMap<String , MyAnimal?>? = null
    var address : String? = null

    constructor()

    constructor(
        uid : String? ,
        displayName : String? ,
        email : String? ,
        photoUrl : String? ,
        phoneNumber : String?
    ) {
        this.uid = uid
        this.displayName = displayName
        this.email = email
        this.photoUrl = photoUrl
        this.phoneNumber = phoneNumber
    }

    constructor(
        uid : String? ,
        displayName : String? ,
        email : String? ,
        photoUrl : String? ,
        phoneNumber : String? ,
        myAnimals : HashMap<String , MyAnimal?>? ,
        address : String?
    ) {
        this.uid = uid
        this.displayName = displayName
        this.email = email
        this.photoUrl = photoUrl
        this.phoneNumber = phoneNumber
        this.myAnimals = myAnimals
        this.address = address
    }


}