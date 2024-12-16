package com.geeks.notapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.notapp.R
import com.geeks.notapp.databinding.FragmentNoteBinding
import com.geeks.notapp.utils.PreferenceaHelper
import java.util.prefs.Preferences

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        val shredPref = PreferenceaHelper()
        shredPref.unit(requireContext())
        btnSave.setOnClickListener {
            val et = edText.text.toString()
            shredPref.text = et
            txtText.text = et
        }
        val editText = shredPref.text
        edText.setText(editText)
        txtText.text = shredPref.text
    }
}