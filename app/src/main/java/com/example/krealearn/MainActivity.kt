package com.example.krealearn

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.krealearn.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val username = sharedPref.getString("username", "Pengguna")
        binding.tvGreeting.text = "Halo, $username"

        binding.btnIkuti.setOnClickListener {
            Snackbar.make(binding.root, "Kelas berhasil diikuti", Snackbar.LENGTH_LONG)
                .setAction("BATAL") {
                    // batalkan ikuti kelas
                }
                .show()
        }

        binding.btnLanjut.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Lanjut belajar")
                .setMessage("Lanjutkan Dasar Lettering iPad & Procreate di Modul 3?")
                .setNegativeButton("Nanti", null)
                .setPositiveButton("Lanjut") { dialog, _ ->
                    dialog.dismiss()
                    startActivity(Intent(this@MainActivity, MateriActivity::class.java))
                }
                .setCancelable(false)
                .show()
        }

        binding.btnKembali.setOnClickListener {
            finish()
        }

        binding.btnToolbar.setOnClickListener {
            startActivity(Intent(this@MainActivity, ToolbarActivity::class.java))
        }

        binding.btnMateri.setOnClickListener {
            startActivity(Intent(this@MainActivity, MateriActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}