package com.example.noteappforpatern.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappforpatern.R
import com.example.noteappforpatern.data.local.entity.NoteEntity
import com.example.noteappforpatern.databinding.FragmentNoteBinding
import com.example.noteappforpatern.ui.fragments.note.adapter.NoteAdapter
import com.example.noteappforpatern.ui.fragments.note.adapter.OnClick
import org.koin.android.ext.android.inject

class NoteFragment : Fragment(), NoteViewInterface, OnClick {

    private lateinit var adapter: NoteAdapter
    private lateinit var binding: FragmentNoteBinding
    private var flag = true
    private val presenter: NotePresenter by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NoteAdapter(this, this)
        binding.rvNotes.adapter = adapter
        initListener()
    }

    private fun initListener() = with(binding) {
        btnPlus.setOnClickListener {
            findNavController().navigate(R.id.detailFragment)
        }
        imgShape.setOnClickListener {
            if (flag) {
                imgShape.setImageResource(R.drawable.ic_shape)
                binding.rvNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                flag = false
            } else {
                imgShape.setImageResource(R.drawable.ic_shape_line)
                binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
                flag = true
            }
        }
    }

    override fun showNote(noteModels: LiveData<List<NoteEntity>>) {
        noteModels.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes) // Обновите список в адаптере
        }
    }

    override fun deleteNote(noteModel: NoteEntity) {
        presenter.getNote()
    }

    override fun onItemClick(noteModel: NoteEntity) =
        findNavController().navigate(
            NoteFragmentDirections.actionNoteFragmentToDetailFragment(
                noteModel.id
            )
        )

    override fun onItemLongClick(noteModel: NoteEntity) {
        presenter.deleteNote(noteModel)
    }
}