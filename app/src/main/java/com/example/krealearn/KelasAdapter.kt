package com.example.krealearn

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.krealearn.databinding.ItemKelasBinding

class KelasAdapter(
    context: Context,
    private val kelasList: List<KelasModel>
) : ArrayAdapter<KelasModel>(context, 0, kelasList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemKelasBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        val data = kelasList[position]

        // Muat gambar dari URL ke ImageView
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textKelas.text = data.namaKelas
        binding.textKreator.text = data.kreator

        view.setOnClickListener {
            val i = Intent(context, KelasDetailActivity::class.java)
            i.putExtra("KELAS", data.namaKelas)
            i.putExtra("KREATOR", data.kreator)
            context.startActivity(i)
        }
        return view
    }
}