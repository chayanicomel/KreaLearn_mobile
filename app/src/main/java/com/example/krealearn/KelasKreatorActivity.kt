package com.example.krealearn

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krealearn.data.KreaDatabase
import com.example.krealearn.data.entity.KelasKreatorEntity
import com.example.krealearn.databinding.ActivityKelasKreatorBinding
import kotlinx.coroutines.launch

class KelasKreatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKelasKreatorBinding
    private lateinit var db: KreaDatabase
    private lateinit var adapter: KelasKreatorAdapter
    private val daftarKelas = mutableListOf<KelasKreatorEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityKelasKreatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Dashboard Kreator"
            subtitle = "Kelas yang kamu buat"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        db = KreaDatabase.getInstance(this)
        adapter = KelasKreatorAdapter(daftarKelas) { kelas -> hapusKelas(kelas) }
        binding.rvKelasKreator.layoutManager = LinearLayoutManager(this)
        binding.rvKelasKreator.adapter = adapter

        binding.fabTambahKelas.setOnClickListener {
            startActivity(Intent(this, FormKelasActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        ambilKelas()
    }

    private fun ambilKelas() {
        lifecycleScope.launch {
            val hasil = db.kelasDao().getAll()
            daftarKelas.clear()
            daftarKelas.addAll(hasil)
            adapter.notifyDataSetChanged()
        }
    }

    private fun hapusKelas(kelas: KelasKreatorEntity) {
        lifecycleScope.launch {
            db.kelasDao().delete(kelas)
            ambilKelas()
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