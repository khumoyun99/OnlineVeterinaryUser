package com.example.onlineveterinaryuser.presentation.nav_doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemRvDoctorsBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Doctor

class DoctorsRvAdapter(val listener : OnDoctorsTouchListener):
    RecyclerView.Adapter<DoctorsRvAdapter.MyDoctorsViewHolder>() {

    private val doctorsList = mutableListOf<Doctor>()

    fun muSubmitList(doctorList : ArrayList<Doctor>) {
        doctorsList.apply {
            clear()
            addAll(doctorList)
        }
    }

    inner class MyDoctorsViewHolder(private val itemRvDoctorsBinding : ItemRvDoctorsBinding):
        RecyclerView.ViewHolder(itemRvDoctorsBinding.root) {

        init {
            itemView.setOnClickListener {
                listener.onClick(doctorsList[absoluteAdapterPosition])
            }

            itemRvDoctorsBinding.apply {
                imgSendImageIcon.setOnClickListener {
                    listener.onMessageClick(doctorsList[absoluteAdapterPosition])
                }
            }
        }

        fun onBind(doctor : Doctor) {
            itemRvDoctorsBinding.apply {
                tvDoctorsName.text = doctor.name
                imgDoctorPhoto.setImageResource(doctor.image)
                tvDoctorsProfession.text = doctor.profession
                ratingBarDoctor.rating = doctor.rating
                tvRatingNumber.text = "(25)"
            }

        }
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyDoctorsViewHolder {
        return MyDoctorsViewHolder(
            ItemRvDoctorsBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder : MyDoctorsViewHolder , position : Int) {
        holder.onBind(doctorsList[position])
    }

    override fun getItemCount() : Int {
        return doctorsList.size
    }

    interface OnDoctorsTouchListener {
        fun onClick(doctor : Doctor)
        fun onMessageClick(doctor : Doctor)
    }


}