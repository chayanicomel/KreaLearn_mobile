package com.example.krealearn

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.krealearn.databinding.ActivityKatalogKelasBinding
import com.google.android.material.tabs.TabLayoutMediator

class KatalogKelasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKatalogKelasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityKatalogKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Katalog Kelas"
            subtitle = "Temukan kelas kreatif untukmu"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        // 1. Inisialisasi adapter
        val tabsAdapter = KatalogTabsAdapter(this)
        // 2. Pasang adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter
        // 3. Hubungkan TabLayout dan ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Sedang Belajar"
                    // Tambah ikon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_nav_book)
                    // Badge dengan angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 3
                }
                1 -> {
                    tab.text = "Selesai"
                    // Tambah ikon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_selesai)
                    // Badge dengan angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 2
                }
                2 -> {
                    tab.text = "Katalog"
                    // Tambah ikon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_nav_explore)
                    // Badge tanpa angka (hanya titik)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
            }
        }.attach()
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