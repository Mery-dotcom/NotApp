package com.geeks.notapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.notapp.R
import com.geeks.notapp.data.models.NoteModel
import com.geeks.notapp.databinding.ItemNoteBinding

class NoteAdapter: ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.noteTitle.text = item.title
            binding.noteDescription.text = item.description
            binding.noteTime.text = "${item.date} ${item.time}"

            val colors = listOf(R.color.light_blue, R.color.light_green, R.color.light_yellow)
            binding.root.setBackgroundColor(
                binding.root.context.resources.getColor(colors[adapterPosition % colors.size])
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class DiffCallback: DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
           return oldItem.id == newItem.id
        }

    }
}
