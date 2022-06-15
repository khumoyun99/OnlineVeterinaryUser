package com.example.onlineveterinaryuser.presentation.nav_medicine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageMedicineBinding
import com.example.onlineveterinaryuser.presentation.nav_medicine.adapters.MedicineRvAdapter
import com.example.onlineveterinaryuser.presentation.nav_medicine.models.Medicines
import com.example.onlineveterinaryuser.utils.scope

class MedicinePage:Fragment(R.layout.page_medicine) {

    private val binding by viewBinding(PageMedicineBinding::bind)
    private lateinit var medicineRvAdapter : MedicineRvAdapter
    private lateinit var medicinesList : ArrayList<Medicines>


    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)


        loadData()
        medicineRvAdapter = MedicineRvAdapter(object:MedicineRvAdapter.OnItemTouchClickListener {
            override fun onItem(medicines : Medicines) {
                findNavController().navigate(
                    MedicinePageDirections.actionMedicinePageToInfoMedicineScreen(
                        medicines
                    )
                )
            }
        })
        medicineRvAdapter.mySubmitList(medicinesList)
        rvMedicine.apply {
            setHasFixedSize(true)
            adapter = medicineRvAdapter
        }

    }

    private fun loadData() {
        medicinesList = ArrayList()
        val namesList = arrayListOf(
            "Otoclean Ear Cleaner 18 x 5ml vials (Dogs & Cats)" ,
            "Remend Corneal Repair Eye Gel 10 x 3ml (Dogs, Cats & Horses)" ,
            "Remend Eye Drops 4 x 10ml (Dogs)" ,
            "Surosolve Ear Cleaner 125ml - Dogs & Cats" ,
            "Lubrithal Eye Gel 10g (Dogs & Cats)"


        )
        val descriptionList =
            arrayListOf(
                "Otoclean Ear Cleaner - bu ortiqcha mum va qoldiqlarni bo'shatish va olib tashlash uchun mo'ljallangan, qulay, ishlatish uchun qulay va kam tirnash xususiyati beruvchi quloqlarni tozalash vositasi." ,
                "Remend Corneal gel itlar, mushuklar va otlardagi shox pardaning yuzaki yaralarini davolashda yordam sifatida foydalanish uchun ko'rsatiladi." ,
                "Remend Dry Eye Lubricant Drops itlar uchun ko'zlar uchun uzoq muddatli namlik va moylash imkonini beradi va kuniga ikki marta foydalanilganda Quruq ko'z belgilarini yumshatishga yordam beradi." ,
                "Surosolve quloq kanalini tozalaydi va namlaydi, shuningdek, sezilarli antibakterial va antifungal ta'sirga ega." ,
                "Lubrithal Eye Gel - ko'zning yosh qatlamini namlash va saqlashga yordam beradigan karbomer moylash suyuqligi."
            )
        val priceList = arrayListOf(
            "182 500 so'm" ,
            "867 500 so'm" ,
            "683 900 so'm" ,
            "189 000 so'm" ,
            "104 000 so'm"


        )
        val doctorsNameList = arrayListOf(
            "R.A.Ashurmatova" ,
            "A.F.Ganiyeva" ,
            "R.A.Ashurmatova" ,
            "A.F.Ganiyeva" ,
            "R.A.Ashurmatova"
        )
        val imgList = arrayListOf(
            R.drawable.img_dogs_otoclean_pr ,
            R.drawable.img_medicine_remand ,
            R.drawable.img_medicine_eye_drops ,
            R.drawable.img_medicine_surosolve ,
            R.drawable.img_medicine_lubrithal ,
        )

        for (i in 0 until 5) {
            medicinesList.add(
                Medicines(
                    i ,
                    namesList[i] ,
                    descriptionList[i] ,
                    priceList[i] ,
                    doctorsNameList[i] ,
                    imgList[i]
                )
            )
        }

    }
}