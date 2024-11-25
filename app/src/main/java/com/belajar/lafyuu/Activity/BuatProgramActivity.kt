package com.belajar.lafyuu.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.lafyuu.Adapter.BuatProgramAdapter
import com.belajar.lafyuu.Adapter.ItemsAdapter
import com.belajar.lafyuu.Models.Items
import com.belajar.lafyuu.R
import com.belajar.lafyuu.databinding.ActivityBuatProgramBinding
import com.belajar.lafyuu.databinding.ViewholderBuatprogramBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BuatProgramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuatProgramBinding
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBuatProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initviewBuatProgram()

        binding.tambahProgram.setOnClickListener {
            val intent = Intent(this@BuatProgramActivity, TambahProgramActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun initviewBuatProgram() {
        val myRef: DatabaseReference = database.getReference("Items")
        val items = ArrayList<Items>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val item = issue.getValue(Items::class.java)
                        if (item != null) {
                            items.add(item)
                        }
                    }
                    if (items.isNotEmpty()) {
                        binding.viewBuatProgram.layoutManager = LinearLayoutManager(
                            this@BuatProgramActivity, LinearLayoutManager.VERTICAL, false
                        )
                        binding.viewBuatProgram.adapter = BuatProgramAdapter(items, this@BuatProgramActivity)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@BuatProgramActivity, "Failed to load data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}