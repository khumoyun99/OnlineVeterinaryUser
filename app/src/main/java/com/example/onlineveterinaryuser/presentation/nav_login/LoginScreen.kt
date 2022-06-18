package com.example.onlineveterinaryuser.presentation.nav_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.MainActivity
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenLoginBinding
import com.example.onlineveterinaryuser.presentation.activity.model.Account
import com.example.onlineveterinaryuser.presentation.nav_animals.models.MyAnimal
import com.example.onlineveterinaryuser.utils.scope
import com.example.onlineveterinaryuser.utils.showToast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*

class LoginScreen:Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var reference : DatabaseReference


    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_my_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity() , gso)

        cvSignInGoogle.setOnClickListener {
            signIn()
        }

        btnSignInButton.setOnClickListener {
            val intent = Intent(requireActivity() , MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent , 1)
    }

    override fun onActivityResult(requestCode : Int , resultCode : Int , data : Intent?) {
        super.onActivityResult(requestCode , resultCode , data)

        if (requestCode == 1) {
            val task : Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }
    }

    private fun handleSignInResult(completedTask : Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken ?: "")

        } catch (e : ApiException) {
            showToast("E r r o r 1: $e")
        }

    }

    private fun firebaseAuthWithGoogle(idToken : String) {
        val credential = GoogleAuthProvider.getCredential(idToken , null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    reference.addListenerForSingleValueEvent(object:ValueEventListener {
                        override fun onDataChange(snapshot : DataSnapshot) {
                            val children = snapshot.children
                            var isHave = false
                            for (child in children) {
                                if (child.key == user?.uid) {
                                    isHave = true
                                    break
                                }
                            }

                            if (!isHave) {
                                val account = Account(
                                    uid = user?.uid.toString() ,
                                    displayName = user?.displayName.toString() ,
                                    email = user?.email.toString() ,
                                    phoneNumber = user?.phoneNumber.toString() ,
                                    photoUrl = user?.photoUrl.toString()
                                )
                                reference.child(user?.uid ?: "").setValue(account)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            val intent =
                                                Intent(requireActivity() , MainActivity::class.java)
                                            startActivity(intent)
                                        } else {
                                            showToast("${it.exception?.message}")
                                        }
                                    }
                            } else {
                                val intent =
                                    Intent(requireActivity() , MainActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        override fun onCancelled(error : DatabaseError) {
                            showToast(error.message)
                        }
                    })

                } else {
                    showToast("error2:${task.exception?.message.toString()}")
                }
            }
    }
}