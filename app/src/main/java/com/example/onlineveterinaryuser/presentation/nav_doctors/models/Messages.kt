package com.example.onlineveterinaryuser.presentation.nav_doctors.models

class Messages {
    var id : String? = null
    var message : String? = null
    var from : String? = null
    var to : String? = null
    var date : Long? = null


    constructor()
    constructor(id : String? , message : String? , from : String? , to : String? , date : Long?) {
        this.id = id
        this.message = message
        this.from = from
        this.to = to
        this.date = date
    }

}