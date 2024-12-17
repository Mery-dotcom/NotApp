package com.geeks.notapp.ui.fragments.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.notapp.data.Note
import com.geeks.notapp.databinding.FragmentHomeScreenBinding
import com.geeks.notapp.ui.adapters.NoteAdapter

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        val notesList = listOf(
            Note("План на жизнь", "Посадить сына, построить дом, вырастить дерево.", "31 мая 12:45"),
            Note("Нужно сделать", "Работы с проектом, сделать домашку, построить бизнес.", "31 мая 12:45"),
            Note("Отдых", "Посетить горы, прочитать книгу, расслабиться.", "31 мая 12:45")
        )

        noteAdapter = NoteAdapter(notesList)
        rvInfo.adapter = noteAdapter
        rvInfo.layoutManager = LinearLayoutManager(requireContext())
    }
}

