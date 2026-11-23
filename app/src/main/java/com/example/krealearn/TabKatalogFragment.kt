package com.example.krealearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.krealearn.databinding.FragmentTabKatalogBinding

class TabKatalogFragment : Fragment() {
    private var _binding: FragmentTabKatalogBinding? = null
    private val binding get() = _binding!!

    private val kelasList = listOf(
        KelasKatalogModel("Merajut Tas Macrame Bohemian", "Rp 89.000", "https://picsum.photos/seed/macrame/400/300"),
        KelasKatalogModel("Food Photography Pakai HP", "Rp 99.000", "https://picsum.photos/seed/food/400/300"),
        KelasKatalogModel("Dasar Lettering iPad & Procreate", "Rp 79.000", "https://picsum.photos/seed/lettering/400/300"),
        KelasKatalogModel("Dasar Desain Poster untuk Pemula", "Rp 79.000", "https://picsum.photos/seed/poster/400/300"),
        KelasKatalogModel("Videografi HP Sinematik", "Rp 129.000", "https://picsum.photos/seed/video/400/300"),
        KelasKatalogModel("Baking Roti Sourdough", "Rp 110.000", "https://picsum.photos/seed/bread/400/300"),
        KelasKatalogModel("Keramik Clay Mug Estetik", "Rp 95.000", "https://picsum.photos/seed/clay/400/300"),
        KelasKatalogModel("Latihan Gitar Akustik Dasar", "Rp 85.000", "https://picsum.photos/seed/guitar/400/300"),
        KelasKatalogModel("Latte Art untuk Pemula", "Rp 90.000", "https://picsum.photos/seed/coffee/400/300"),
        KelasKatalogModel("Sketsa Ilustrasi Cat Air", "Rp 75.000", "https://picsum.photos/seed/watercolor/400/300"),
        KelasKatalogModel("Produksi Musik Lo-fi", "Rp 120.000", "https://picsum.photos/seed/music/400/300"),
        KelasKatalogModel("Fotografi Produk UMKM", "Rp 99.000", "https://picsum.photos/seed/product/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTabKatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val katalogAdapter = KatalogAdapter(kelasList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Kamu memilih ${selectedItem.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.rvKatalog.apply {
            // Mode Grid 2 kolom
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = katalogAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}