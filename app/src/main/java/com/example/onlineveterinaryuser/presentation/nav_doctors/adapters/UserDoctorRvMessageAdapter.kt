package com.example.onlineveterinaryuser.presentation.nav_doctors.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemRvDoctorMessageBinding
import com.example.onlineveterinaryuser.databinding.ItemRvUserMessageBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Messages
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserDoctorRvMessageAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val user = 0
    private val doctor = 1
    private var userUid : String? = null
    private val simpleDateFormat = SimpleDateFormat("HH:mm")

    inner class MyUserMessageVH(private val itemRvUserMessageBinding : ItemRvUserMessageBinding):
        RecyclerView.ViewHolder(itemRvUserMessageBinding.root) {


        fun onBind(message : Messages) {
            itemRvUserMessageBinding.apply {
                tvUserItemMessage.text = message.message
                tvSendMessageDate.text = simpleDateFormat.format(Date(message.date ?: 0))
            }
        }
    }

    inner class MyDoctorMessageVH(private val itemRvDoctorMessageBinding : ItemRvDoctorMessageBinding):
        RecyclerView.ViewHolder(itemRvDoctorMessageBinding.root) {

        fun onBind(message : Messages) {
            itemRvDoctorMessageBinding.apply {
                tvDoctorItemMessage.text = message.message
                tvArriveMessageDate.text = simpleDateFormat.format(Date(message.date ?: 0))
            }
        }
    }

    val DIFF_CALLBACK = object:DiffUtil.ItemCallback<Messages>() {
        override fun areItemsTheSame(oldItem : Messages , newItem : Messages) : Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem : Messages , newItem : Messages) : Boolean {
            return oldItem == newItem
        }
    }

    private val allMessageList = AsyncListDiffer(this , DIFF_CALLBACK)

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : RecyclerView.ViewHolder {
        return when (viewType) {
            user -> {
                MyUserMessageVH(
                    ItemRvUserMessageBinding.inflate(
                        LayoutInflater.from(parent.context) ,
                        parent ,
                        false
                    )
                )
            }
            else -> {
                MyDoctorMessageVH(
                    ItemRvDoctorMessageBinding.inflate(
                        LayoutInflater.from(parent.context) ,
                        parent ,
                        false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder , position : Int) {
        if (getItemViewType(position) == user) {
            (holder as MyUserMessageVH).onBind(allMessageList.currentList[position])
        } else {
            (holder as MyDoctorMessageVH).onBind(allMessageList.currentList[position])
        }
    }

    override fun getItemCount() : Int {
        return allMessageList.currentList.size
    }

    override fun getItemViewType(position : Int) : Int {
        return if (allMessageList.currentList[position].from == userUid) {
            user
        } else {
            doctor
        }
    }

    fun mySubmitList(messageList : ArrayList<Messages> , userUid : String?) {
        allMessageList.submitList(messageList)
        this.userUid = userUid

    }

}