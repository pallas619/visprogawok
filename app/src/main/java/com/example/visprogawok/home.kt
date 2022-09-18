package com.example.visprogawok

import Adapter.ListDataRV
import Database.GlobalVar
import Interface.CardListener
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visprogawok.databinding.ActivityHomeBinding

class home : AppCompatActivity(), CardListener {


    private lateinit var vBind: ActivityHomeBinding
    private val adapter = ListDataRV(GlobalVar.dalist, this)
    private var list: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(vBind.root)
        supportActionBar?.hide()
        permission()
        setupRecyclerView()
        listener()
    }

    override fun onResume() {
        super.onResume()
        list = GlobalVar.dalist.size
        if (list == 0) {
            vBind.addMovie.alpha = 1f
        } else {
            vBind.addMovie.alpha = 0f
        }
        adapter.notifyDataSetChanged()
    }

    private fun listener() {
        vBind.dtadd.setOnClickListener {
            val myIntent = Intent(this, addath::class.java)
            startActivity(myIntent)
        }
    }

    private fun permission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                GlobalVar.STORAGE_PERMISSION_CODE
            )
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                GlobalVar.STORAGE_PERMISSION_CODE
            )
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false)
        vBind.listDataRV.layoutManager = layoutManager   // Set layout
        vBind.listDataRV.adapter = adapter   // Set adapter
    }

    override fun onCardClick(position: Int) {
        val myIntent = Intent(this, addath::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }
}