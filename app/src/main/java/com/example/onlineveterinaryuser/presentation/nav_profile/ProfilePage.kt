package com.example.onlineveterinaryuser.presentation.nav_profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageProfileBinding
import com.example.onlineveterinaryuser.presentation.activity.model.Account
import com.example.onlineveterinaryuser.utils.gone
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
import com.example.onlineveterinaryuser.utils.visible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.math.acos

class ProfilePage:Fragment(R.layout.page_profile) {

    private val binding by viewBinding(PageProfileBinding::bind)
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var reference : DatabaseReference
    private lateinit var account : Account

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)
        setHasOptionsMenu(true)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        reference.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                val children = snapshot.children
                for (child in children) {
                    val user = child.getValue(Account::class.java)
                    if (user != null && user.uid == firebaseAuth.uid) {
                        account = user
                    }
                }

                try {
                    Picasso.get().load(account.photoUrl).error(R.drawable.ic_profile_person)
                        .placeholder(R.drawable.ic_profile_person).into(imgProfileImage)
                    tvProfileName.text = account.displayName
                    etProfileName.setText(account.displayName)
                    etProfileEmail.setText(account.email)
                    etProfilePhoneNumber.setText(account.phoneNumber)
                    etProfileAddress.setText(account.address)

                } catch (e : Exception) {
                    showToast(e.message.toString())
                }

            }

            override fun onCancelled(error : DatabaseError) {
                showToast("Tizimda xatolik")
            }
        })


        imgEditPencil.setOnClickListener {
            imgEditPencil.gone()
            btnProfileSave.visible()

            etProfileName.isEnabled = true
            etProfilePhoneNumber.isEnabled = true
            etProfileAddress.isEnabled = true

        }

        btnProfileSave.setOnClickListener {
            val name = etProfileName.text.toString()
            val phoneNumber = etProfilePhoneNumber.text.toString()
            val address = etProfileAddress.text.toString()

            imgEditPencil.visible()
            btnProfileSave.gone()

            etProfileName.isEnabled = false
            etProfilePhoneNumber.isEnabled = false
            etProfileAddress.isEnabled = false

            reference.child(firebaseAuth.currentUser?.uid ?: "").setValue(
                Account(
                    uid = account.uid ,
                    displayName = name ,
                    phoneNumber = phoneNumber ,
                    email = account.email ,
                    photoUrl = account.photoUrl ,
                    myAnimals = account.myAnimals ,
                    address = address
                )
            ).addOnCompleteListener {

                if (it.isSuccessful) {
                    showToast("ok")
                } else {
                    showToast("error")
                }
            }

        }

    }

    override fun onCreateOptionsMenu(menu : Menu , inflater : MenuInflater) {
        super.onCreateOptionsMenu(menu , inflater)
        inflater.inflate(R.menu.profile_language_menu , menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        when (item.itemId) {
            R.id.languageMenu -> {
                findNavController().navigate(ProfilePageDirections.actionProfilePageToLanguageProfileScreen())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}