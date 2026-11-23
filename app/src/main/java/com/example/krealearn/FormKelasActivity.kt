package com.example.krealearn

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.krealearn.data.KreaDatabase
import com.example.krealearn.data.entity.KelasKreatorEntity
import com.example.krealearn.databinding.ActivityFormKelasBinding
import kotlinx.coroutines.launch

class FormKelasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormKelasBinding
    private lateinit var db: KreaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFormKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Buat Kelas Baru"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        db = KreaDatabase.getInstance(this)

        binding.btnSimpanKelas.setOnClickListener {
            val judul = binding.etJudul.text.toString().trim()
            val kategori = binding.etKategori.text.toString().trim()
            val deskripsi = binding.etDeskripsi.text.toString().trim()

            if (judul.isBlank() || kategori.isBlank() || deskripsi.isBlank()) {
                Toast.makeText(
                    this, "Isi semua kolom!", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            binding.btnSimpanKelas.isEnabled = false
            lifecycleScope.launch {
                try {
                    db.kelasDao().insert(
                        KelasKreatorEntity(
                            judul = judul,
                            kategori = kategori,
                            deskripsi = deskripsi
                        )
                    )
                    finish()
                } catch (e: Exception) {
                    binding.btnSimpanKelas.isEnabled = true
                    Toast.makeText(
                        this@FormKelasActivity,
                        "Gagal menyimpan kelas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}