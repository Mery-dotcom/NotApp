package com.geeks.notapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geeks.notapp.App
import com.geeks.notapp.R
import com.geeks.notapp.data.db.AppDataBase
import com.geeks.notapp.data.models.NoteModel
import com.geeks.notapp.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding){
        btnAdd.setOnClickListener{
            val etTitle = etTitle.text.toString()
            val etDescription = etDescription.text.toString()
            val txtDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
            val txtTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            if (etTitle.isNotEmpty() && etDescription.isNotEmpty()){
                App.appDataBase?.noteDao()?.insertNote(NoteModel(0, title = etTitle, description = etDescription, date =  txtDate, time = txtTime))
                findNavController().navigateUp()
            } else{
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}