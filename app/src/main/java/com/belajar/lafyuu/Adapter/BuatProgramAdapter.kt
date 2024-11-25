package com.belajar.lafyuu.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajar.lafyuu.Activity.BuatProgramActivity
import com.belajar.lafyuu.Activity.DetailProgramActivity
import com.belajar.lafyuu.Models.Items
import com.belajar.lafyuu.databinding.ViewholderBuatprogramBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class BuatProgramAdapter(private val items: ArrayList<Items>, private val context: Context) : RecyclerView.Adapter<BuatProgramAdapter.Viewholder>() {

    inner class Viewholder(private val binding: ViewholderBuatprogramBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Items) {
            binding.titleTxt.text = item.title
            binding.descriptionTxt.text = item.description.toString()

            binding.btnDetail.setOnClickListener {
                val intent = Intent(context, DetailProgramActivity::class.java)
                intent.putExtra("object", item)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderBuatprogramBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
