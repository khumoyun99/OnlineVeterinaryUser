package com.example.onlineveterinaryuser.presentation.nav_doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ItemRvDoctorsBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Doctor
import com.squareup.picasso.Picasso

class DoctorsRvAdapter(val listener : OnDoctorsTouchListener):
    RecyclerView.Adapter<DoctorsRvAdapter.MyDoctorsViewHolder>() {


    fun muSubmitList(doctorList : ArrayList<Doctor>) {
        doctorsList.submitList(doctorList)

    }

    val diff_call_back = object:DiffUtil.ItemCallback<Doctor>() {
        override fun areItemsTheSame(oldItem : Doctor , newItem : Doctor) : Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem : Doctor , newItem : Doctor) : Boolean {
            return oldItem == newItem
        }
    }

    private val doctorsList = AsyncListDiffer(this , diff_call_back)

    inner class MyDoctorsViewHolder(private val itemRvDoctorsBinding : ItemRvDoctorsBinding):
        RecyclerView.ViewHolder(itemRvDoctorsBinding.root) {

        init {
            itemView.setOnClickListener {
                listener.onClick(doctorsList.currentList[absoluteAdapterPosition])
            }

            itemRvDoctorsBinding.apply {
                imgSendImageIcon.setOnClickListener {
                    listener.onMessageClick(doctorsList.currentList[absoluteAdapterPosition])
                }
            }
        }

        fun onBind(doctor : Doctor) {
            itemRvDoctorsBinding.apply {
                tvDoctorsName.text = doctor.name
                Picasso.get().load(doctor.image).error(R.drawable.ic_profile_person)
                    .into(imgDoctorPhoto)
                tvDoctorsProfession.text = doctor.profession
                ratingBarDoctor.rating = doctor.rating
                tvRatingNumber.text = "(10)"
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
        holder.onBind(doctorsList.currentList[position])
    }

    override fun getItemCount() : Int {
        return doctorsList.currentList.size
    }

    interface OnDoctorsTouchListener {
        fun onClick(doctor : Doctor)
        fun onMessageClick(doctor : Doctor)
    }


}