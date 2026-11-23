package com.example.krealearn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.krealearn.data.entity.KelasKreatorEntity
import com.example.krealearn.databinding.ItemKelasKreatorBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class KelasKreatorAdapter(
    private val daftarKelas: List<KelasKreatorEntity>,
    private val onDelete: (KelasKreatorEntity) -> Unit
) : RecyclerView.Adapter<KelasKreatorAdapter.KelasViewHolder>() {

    class KelasViewHolder(val binding: ItemKelasKreatorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KelasViewHolder {
        val binding = ItemKelasKreatorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return KelasViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: KelasViewHolder,
        position: Int
    ) {
        val kelas = daftarKelas[position]
        holder.binding.tvKategori.text = kelas.kategori
        holder.binding.tvJudul.text = kelas.judul
        holder.binding.tvDeskripsi.text = kelas.deskripsi
        holder.binding.btnHapus.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Kelas")
                .setMessage("Yakin ingin menghapus kelas ini?")
                .setPositiveButton("Ya") { _, _ -> onDelete(kelas) }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getItemCount(): Int = daftarKelas.size
}