package com.geeks.notapp.ui.fragments.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.notapp.App
import com.geeks.notapp.R
import com.geeks.notapp.data.models.NoteModel
import com.geeks.notapp.data.models.SharedViewModel
import com.geeks.notapp.databinding.FragmentHomeScreenBinding
import com.geeks.notapp.ui.adapters.NoteAdapter
import com.geeks.notapp.ui.interfaces.OnClickItem

class HomeScreenFragment : Fragment(), OnClickItem{

    private lateinit var binding: FragmentHomeScreenBinding
    private val noteAdapter = NoteAdapter(this, this)
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        getData()

        sharedViewModel.selectedColor.observe(viewLifecycleOwner) {color ->
            noteAdapter.submitList(noteAdapter.currentList.map {
                it.copy(color = color)
            })
        }
    }

    private fun initialize() {
        binding.rvInfo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() = with(binding){
        fabAdd.setOnClickListener{
            findNavController().navigate(R.id.action_homeScreenFragment_to_noteDetailFragment)
        }
    }

    private fun getData() {
        App.appDataBase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){model ->
            noteAdapter.submitList(model)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder){
            setTitle("Удалить заметку")
            setPositiveButton("Удалить"){ dialog, _ ->
                App.appDataBase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Отмена"){ dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}

