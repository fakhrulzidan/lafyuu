package com.belajar.lafyuu.Adapter

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.belajar.lafyuu.Activity.DetailActivity
import com.belajar.lafyuu.Models.Items
import com.belajar.lafyuu.databinding.ViewholderItemsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ItemsAdapter(private val items:ArrayList<Items>):RecyclerView.Adapter<ItemsAdapter.Viewholder>() {
    private var context:Context?=null

    inner class Viewholder(private val binding: ViewholderItemsBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Items){
            binding.titleTxt.text=item.title
            binding.priceTxt.text = "$${item.price}"
            binding.ratingTxt.text = item.rating.toString()

            val requestOptions=RequestOptions()
                .transform(CenterCrop(),RoundedCorners(30))

            Glide.with(context!!)
                .load(item.picUrl)
                .apply(requestOptions)
                .into(binding.pic)

            binding.root.setOnClickListener {
                val intent=Intent(context,DetailActivity::class.java)
                intent.putExtra("object",item)
                context!!.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.Viewholder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =items.size
}
//class ItemsAdapter(val items: MutableList<Items>) :
//    RecyclerView.Adapter<ItemsAdapter.Viewholder>() {
//
//    class Viewholder(val binding: ViewholderItemsBinding) :
//        RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
//            ItemsAdapter.Viewholder {
//        val binding = ViewholderItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return Viewholder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ItemsAdapter.Viewholder, position: Int) {
//        val item = items[position]
//
//        with(holder.binding) {
//            titleTxt.text = item.title
//            priceTxt.text = "$${item.price}"
//            ratingTxt.text = item.rating.toString()
//
//            Glide.with(holder.itemView.context)
//                .load(item.picUrl)
//                .into(pic)
//            root.setOnClickListener {
//                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
//                    putExtra("object", item)
//                }
//                ContextCompat.startActivity(holder.itemView.context,intent,null)
//                }
//        }
//    }
//
//    override fun getItemCount(): Int = items.size
//}