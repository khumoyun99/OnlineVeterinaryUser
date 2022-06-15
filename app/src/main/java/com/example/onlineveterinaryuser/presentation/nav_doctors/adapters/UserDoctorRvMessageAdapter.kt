package com.example.onlineveterinaryuser.presentation.nav_doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemRvDoctorMessageBinding
import com.example.onlineveterinaryuser.databinding.ItemRvUserMessageBinding

class UserDoctorRvMessageAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val user = 0
    private val doctor = 1
    private val allMessageList = mutableListOf<String>()


    inner class MyUserMessageVH(private val itemRvUserMessageBinding : ItemRvUserMessageBinding):
        RecyclerView.ViewHolder(itemRvUserMessageBinding.root) {

        fun onBind(message : String) {
            itemRvUserMessageBinding.apply {
                tvUserItemMessage.text = message
            }
        }
    }

    inner class MyDoctorMessageVH(private val itemRvDoctorMessageBinding : ItemRvDoctorMessageBinding):
        RecyclerView.ViewHolder(itemRvDoctorMessageBinding.root) {

        fun onBind(message : String) {
            itemRvDoctorMessageBinding.apply {
                tvDoctorItemMessage.text = message
            }
        }
    }

//    val DIFF_CALLBACK = object:DiffUtil.ItemCallback<String>() {
//        override fun areItemsTheSame(oldItem : String , newItem : String) : Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem : String , newItem : String) : Boolean {
//            return oldItem == newItem
//        }
//    }

//    private val allMessageList = AsyncListDiffer(this , DIFF_CALLBACK)

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
        if (getItemViewType(position) == 0) {
            (holder as MyUserMessageVH).onBind(allMessageList[position])
        } else {
            (holder as MyDoctorMessageVH).onBind(allMessageList[position])
        }
    }

    override fun getItemCount() : Int {
        return allMessageList.size
    }

    override fun getItemViewType(position : Int) : Int {
        return if (position % 2 == 0) {
            user
        } else {
            doctor
        }
    }

    fun mySubmitList(messageList : ArrayList<String>) {
        allMessageList.apply {
            clear()
            addAll(messageList)
        }
    }

}