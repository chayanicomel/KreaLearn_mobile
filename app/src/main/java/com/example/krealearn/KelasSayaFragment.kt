package com.example.krealearn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.krealearn.databinding.FragmentKelasSayaBinding

class KelasSayaFragment : Fragment() {

    private var _binding: FragmentKelasSayaBinding? = null
    private val binding get() = _binding!!

    private val kelasList = listOf(
        KelasModel("Dasar Lettering iPad & Procreate", "Dewi Nurhaliza", "https://i.pravatar.cc/150?img=1"),
        KelasModel("Merajut Tas Macrame Bohemian", "Mbak Siti", "https://i.pravatar.cc/150?img=2"),
        KelasModel("Food Photography Pakai HP", "Chef Adrian", "https://i.pravatar.cc/150?img=3"),
        KelasModel("Dasar Desain Poster untuk Pemula", "Arlo Prasetyo", "https://i.pravatar.cc/150?img=4"),
        KelasModel("Videografi HP Sinematik", "Nadia Kirana", "https://i.pravatar.cc/150?img=5"),
        KelasModel("Baking Roti Sourdough", "Chef Adrian", "https://i.pravatar.cc/150?img=6"),
        KelasModel("Keramik Clay Mug Estetik", "Mas Budi", "https://i.pravatar.cc/150?img=7"),
        KelasModel("Musik & Audio Dasar", "Rani Putri", "https://i.pravatar.cc/150?img=8"),
        KelasModel("Lettering Cantik untuk Pemula", "Dewi Nurhaliza", "https://i.pravatar.cc/150?img=9"),
        KelasModel("Seni Merajut Tas Macrame", "Mbak Siti", "https://i.pravatar.cc/150?img=10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKelasSayaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Kelas Saya"

        val adapter = KelasAdapter(requireContext(), kelasList)
        binding.listKelasItems.adapter = adapter

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.kelas_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialKelasActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}