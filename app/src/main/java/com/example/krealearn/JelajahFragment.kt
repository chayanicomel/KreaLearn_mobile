package com.example.krealearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.krealearn.databinding.FragmentJelajahBinding

class JelajahFragment : Fragment() {

    private var _binding: FragmentJelajahBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf("title" to "Desain & Ilustrasi", "desc" to "Poster, lettering, dan ilustrasi digital"),
        mapOf("title" to "Videografi & Reels", "desc" to "Rekam dan edit video kreatif dari HP"),
        mapOf("title" to "Kriya & Rajut", "desc" to "Macrame, rajut, dan kerajinan tangan"),
        mapOf("title" to "Baking & Barista", "desc" to "Kuliner kreatif dan seduh kopi"),
        mapOf("title" to "Musik & Audio", "desc" to "Dasar produksi musik dan audio"),
        mapOf("title" to "Lettering Cantik", "desc" to "Tulisan tangan indah untuk karya kreatif")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJelajahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Jelajah"

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"), // key yang diambil dari data
            intArrayOf(android.R.id.text1, android.R.id.text2) // tempat tampilnya
        )
        binding.listViewItems.adapter = adapter
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(
                requireContext(), "Kamu memilih: $title ($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}