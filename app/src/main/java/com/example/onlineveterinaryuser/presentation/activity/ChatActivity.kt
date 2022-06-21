package com.example.onlineveterinaryuser.presentation.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.presentation.nav_doctors.adapters.UserDoctorRvMessageAdapter
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Doctor
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Messages
import com.example.onlineveterinaryuser.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ChatActivity:AppCompatActivity() {

    private lateinit var userDoctorsRvAdapter : UserDoctorRvMessageAdapter
    private lateinit var allMessageList : ArrayList<Messages>
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var referenceFrom : DatabaseReference
    private lateinit var referenceTo : DatabaseReference

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        window.statusBarColor = Color.parseColor("#5726E4")

        val doctor : Doctor = intent.getSerializableExtra("doctor") as Doctor
        val toolbarChat = findViewById<Toolbar>(R.id.toolbar_chat)
        val recyclerChat = findViewById<RecyclerView>(R.id.recycler_chat)
        val buttonChatSend = findViewById<Button>(R.id.button_chat_send)
        val editGchatMessage = findViewById<EditText>(R.id.edit_gchat_message)


        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        referenceFrom =
            firebaseDatabase.getReference("users/${firebaseAuth.currentUser?.uid}/allMessages/${doctor.id}")
        referenceTo =
            firebaseDatabase.getReference("doctors/${doctor.id}/allMessages/${firebaseAuth.currentUser?.uid}")

        toolbarChat.title = doctor.name
        toolbarChat.setNavigationOnClickListener {
            onBackPressed()
        }

        buttonChatSend.setOnClickListener {
            val myMessage = editGchatMessage.text.toString()
            val keyFrom = referenceFrom.push().key
            val keyTo = referenceTo.push().key
            if (myMessage.isNotEmpty()) {
                val message = Messages(
                    id = keyFrom ,
                    message = myMessage ,
                    from = "user${firebaseAuth.currentUser?.uid}" ,
                    to = "doctor${doctor.id}" ,
                    date = System.currentTimeMillis()
                )

                referenceFrom.child(keyFrom ?: "").setValue(message)
                referenceTo.child(keyTo ?: "").setValue(message)

                editGchatMessage.text.clear()

            } else {
                showToast("Maydonni to'ldiring")
            }
        }


        allMessageList = ArrayList()
        userDoctorsRvAdapter = UserDoctorRvMessageAdapter()
        recyclerChat.apply {
            setHasFixedSize(true)
            adapter = userDoctorsRvAdapter
        }

        referenceFrom.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                try {
                    allMessageList.clear()
                    val children = snapshot.children
                    for (child in children) {
                        val messages = child.getValue(Messages::class.java)
                        if (messages != null) {
                            allMessageList.add(messages)
                        }
                    }
                    userDoctorsRvAdapter.mySubmitList(
                        allMessageList ,
                        "user${firebaseAuth.currentUser?.uid}"
                    )
                    userDoctorsRvAdapter.notifyDataSetChanged()
                    recyclerChat.scrollToPosition(userDoctorsRvAdapter.itemCount - 1)

                } catch (e : Exception) {
                    showToast(e.message.toString())
                }
            }

            override fun onCancelled(error : DatabaseError) {
                showToast(error.message)
            }
        })

    }
}