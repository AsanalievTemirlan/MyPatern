package com.example.noteappforpatern.ui.fragments.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappforpatern.data.local.entity.NoteEntity
import com.example.noteappforpatern.databinding.ItemNotesBinding

class NoteAdapter(private val onClick: OnClick, private val onLongClick: OnClick) : ListAdapter<NoteEntity, NoteAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class ViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(noteModel: NoteEntity) {
            binding.apply {
                tvTitle.text = noteModel.title
                tvDescription.text = noteModel.content
                tvData.text = noteModel.date
                tvTime.text = noteModel.time
                itemView.setBackgroundColor(noteModel.color)
                itemView.setOnClickListener {
                    onClick.onItemClick(noteModel)
                }
                itemView.setOnLongClickListener {
                    onLongClick.onItemLongClick(noteModel)
                    true
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.title == newItem.title && oldItem.content == newItem.content
        }
    }
}