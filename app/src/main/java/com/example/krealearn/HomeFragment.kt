package com.example.krealearn

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.krealearn.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        val username = sharedPref.getString("username", "Pengguna")
        binding.tvGreeting.text = "Halo, $username"

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()

            }
        }

        binding.btnIkuti.setOnClickListener {
            Snackbar.make(binding.root, "Kelas berhasil diikuti", Snackbar.LENGTH_LONG)
                .setAction("BATAL") {

                }
                .show()
        }

        binding.btnLanjut.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Lanjut belajar")
                .setMessage("Lanjutkan Dasar Lettering iPad & Procreate di Modul 3?")
                .setNegativeButton("Nanti", null)
                .setPositiveButton("Lanjut") { dialog, _ ->
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), MateriActivity::class.java))
                }
                .setCancelable(false)
                .show()
        }
    }
}