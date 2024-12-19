package com.geeks.notapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geeks.notapp.R
import com.geeks.notapp.databinding.FragmentNoteBinding
import com.geeks.notapp.utils.PreferenceaHelper
import java.util.prefs.Preferences

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var prefHelper: PreferenceaHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefHelper = PreferenceaHelper().apply {
            unit(requireContext())
        }

        if (!prefHelper.isOnBoardShown) {
            findNavController().navigate(R.id.action_noteFragment_to_homeScreenFragment)
        }

        binding.edText.setText(prefHelper.text)
        binding.txtText.text = prefHelper.text

        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnSave.setOnClickListener{
            val enteredText = edText.text.toString()
            prefHelper.text = enteredText
            prefHelper.isRegistered = true
            txtText.text = enteredText
            findNavController().navigate(R.id.action_noteFragment_to_homeScreenFragment)
        }
    }
}