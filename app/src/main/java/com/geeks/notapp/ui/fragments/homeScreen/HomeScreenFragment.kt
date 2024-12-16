package com.geeks.notapp.ui.fragments.homeScreen

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.Lottie.initialize
import com.geeks.notapp.R
import com.geeks.notapp.data.Note
import com.geeks.notapp.databinding.FragmentHomeScreenBinding
import com.geeks.notapp.ui.adapters.NoteAdapter


class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        val notes = listOf(
            Note("План на жизнь:", "Посадить сына, построить дом, вырастить дерево", "31 мая 12:45", Color.parseColor("#FFCCCB")),
            Note("Нужно сделать:", "Сделать проект, построить бизнес", "31 мая 12:45", Color.parseColor("#FFFACD")),
            Note("Нужно сделать:", "Доделать домашку, работать над проектом", "31 мая 12:45", Color.parseColor("#98FB98"))
        )

        adapter = NoteAdapter(notes)
        binding.rvInfo.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInfo.adapter = adapter
        binding.fabAdd.setOnClickListener {
            Toast.makeText(requireContext(), "Добавить новую заметку", Toast.LENGTH_SHORT).show()
        }
    }
}