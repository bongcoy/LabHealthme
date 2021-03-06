package com.example.labhealthme.maps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labhealthme.databinding.ActivityChooseSearchLocBinding
import com.example.labhealthme.doctor.DoctorActivity
import com.example.labhealthme.hospital.Hospital
import com.example.labhealthme.hospital.HospitalVerticalAdapter
import com.example.labhealthme.hospital.HospitalsData

class ChooseSearchLocActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseSearchLocBinding
    private var listHospital: ArrayList<Hospital> = arrayListOf()
    private val actTitle = "Pilih Rumah Sakit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseSearchLocBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarTitle(actTitle)

        binding.rvHospitalsVertical.setHasFixedSize(true)
        listHospital.addAll(HospitalsData.listData)
        showRecyclerList()
    }

    private fun moveToDoctor(hospital: Hospital, idxListDoctor: Int) {
        val moveIntent = Intent(this, DoctorActivity::class.java)
        moveIntent.putExtra(DoctorActivity.EXTRA_IDX_DOCTOR, idxListDoctor)
        moveIntent.putExtra(DoctorActivity.EXTRA_TITLE, hospital.name)
        startActivity(moveIntent)
    }

    private fun showRecyclerList() {
        val listHospitalVerticalAdapter = HospitalVerticalAdapter(listHospital)
        binding.rvHospitalsVertical.apply {
            layoutManager = LinearLayoutManager(this@ChooseSearchLocActivity)
            adapter = listHospitalVerticalAdapter
        }

        listHospitalVerticalAdapter.setOnItemClickCallback(object :
            HospitalVerticalAdapter.OnItemClickCallback {
            override fun onItemClicked(item: Hospital, position: Int) {
                moveToDoctor(item, position)
            }
        })
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}
