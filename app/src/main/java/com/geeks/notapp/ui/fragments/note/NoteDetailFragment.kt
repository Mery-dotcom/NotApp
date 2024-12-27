package com.geeks.notapp.ui.fragments.note

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geeks.notapp.App
import com.geeks.notapp.data.models.NoteModel
import com.geeks.notapp.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var noteId: Int = -1
    private var selectedColor: Int = Color.WHITE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        binding.txtDate.text = date
        binding.txtTime.text = time

        updateNote()
        setupListener()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1){
            val  id = App.appDataBase?.noteDao()?.getById(noteId)
            id?.let { model ->
                binding.etTitle.setText(model.title)
                binding.etDescription.setText(model.description)
                selectedColor = model.color!!
                binding.root.setBackgroundColor(selectedColor)
            }
        }
    }

    private fun setupListener() = with(binding){
        btnAdd.setOnClickListener{
            val etTitle = etTitle.text.toString()
            val etDescription = etDescription.text.toString()
            val date = txtDate.text.toString()
            val time = txtTime.text.toString()

            if (etTitle.isNotEmpty() && etDescription.isNotEmpty()){
                if (noteId != -1){
                    val updateNote = NoteModel(etTitle, etDescription, date, time, selectedColor).apply { id = noteId }
                    App.appDataBase?.noteDao()?.updateNote(updateNote)
                    Toast.makeText(requireContext(), "Заметка обновлена", Toast.LENGTH_SHORT).show()
                }else{
                    val newNote = NoteModel(etTitle, etDescription, date, time, selectedColor)
                    App.appDataBase?.noteDao()?.insertNote(newNote)
                    Toast.makeText(requireContext(), "Заметка добавлена", Toast.LENGTH_SHORT).show()
                }
                findNavController().navigateUp()
            } else{
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

        btnChooseColor.setOnClickListener{
            chooseColorPicker()
        }

        colorYellow.setOnClickListener { selectColor(Color.parseColor("#F6E360")) }
        colorPurple.setOnClickListener { selectColor(Color.parseColor("#ED5A8A")) }
        colorPink.setOnClickListener { selectColor(Color.parseColor("#DD68EF")) }
        colorRed.setOnClickListener { selectColor(Color.parseColor("#E26259")) }
        colorGreen.setOnClickListener { selectColor(Color.parseColor("#67BF6B")) }
        colorBlue.setOnClickListener { selectColor(Color.parseColor("#51A7EB")) }
    }

    private fun chooseColorPicker() {
       binding.colorPickerContainer.visibility =
           if (binding.colorPickerContainer.visibility == View.GONE) View.VISIBLE else View.GONE
    }

    private fun selectColor(color: Int) {
        selectedColor = color
        binding.colorPickerContainer.visibility = View.GONE
        Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
    }
}