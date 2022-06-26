package com.example.e_live.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.example.data.storage.models.TreeModel
import com.example.e_live.databinding.ItemTreeBinding

class SearchResultAdapter(
    private val products: List<TreeModel?>?,
    private val listener: ListenerItem,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    private var currentPositionTree: Int = 0

    override fun onClick(p0: View?) {
        listener.onDisplayProduct()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemTreeBinding.inflate(inflate, parent, false)
        binding.root.forEach { it.setOnClickListener(this@SearchResultAdapter) }
        return ItemThree2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    private fun loadImage(url: String?, image: ImageView) {
//        Glide.with(context)
//            .load(url)
//            .placeholder(R.drawable.ic_baseline_image_24)
//            .error(R.drawable.ic_baseline_broken_image_24)
//            .into(image)
    }

    override fun getItemCount(): Int {
        return products!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemThree2ViewHolder(
        val binding: ItemTreeBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    interface ListenerItem {
        fun onDisplayProduct()
    }
}

