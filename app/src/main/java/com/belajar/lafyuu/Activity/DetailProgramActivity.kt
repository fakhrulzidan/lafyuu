package com.belajar.lafyuu.Activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.belajar.lafyuu.Models.Items
import com.belajar.lafyuu.R
import com.belajar.lafyuu.databinding.ActivityDetailBinding
import com.belajar.lafyuu.databinding.ActivityDetailProgramBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailProgramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProgramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setVariable()

    }

    private fun setVariable() {
        val item: Items = intent.getParcelableExtra("object")!!

        binding.descriptionTxt.text = item.description

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}