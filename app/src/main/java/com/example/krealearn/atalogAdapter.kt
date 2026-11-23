package com.example.krealearn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.krealearn.databinding.ItemKatalogBinding

class KatalogAdapter(
    private val kelasList: List<KelasKatalogModel>,
    private val onItemClick: (KelasKatalogModel) -> Unit
) : RecyclerView.Adapter<KatalogAdapter.KatalogViewHolder>() {

    inner class KatalogViewHolder(val binding: ItemKatalogBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KatalogViewHolder {
        val binding = ItemKatalogBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return KatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KatalogViewHolder, position: Int) {
        val item = kelasList[position]
        with(holder.binding) {
            tvNamaKelas.text = item.name
            tvHarga.text = item.price

            // Memuat gambar menggunakan Glide
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgKelas)

            // Pasang click listener pada root (kartu)
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = kelasList.size
}