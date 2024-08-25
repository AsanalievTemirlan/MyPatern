package com.example.noteappforpatern.ui.fragments.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappforpatern.R
import com.example.noteappforpatern.data.local.entity.NoteEntity
import com.example.noteappforpatern.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
class NoteFragment : Fragment(), OnClick {
    private lateinit var adapter: NoteAdapter
    private lateinit var binding: FragmentNoteBinding
    private var flag = true
    private val viewModel: NoteViewModel by viewModel()

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

    override fun onResume() {
        super.onResume()
        updateNoteList()
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

    private fun updateNoteList() =
        viewModel.getNote().observe(viewLifecycleOwner) { adapter.submitList(it) }


    override fun onItemClick(noteModel: NoteEntity) =
        findNavController().navigate(
            NoteFragmentDirections.actionNoteFragmentToDetailFragment(
                noteModel.id
            )
        )

    override fun onItemLongClick(noteModel: NoteEntity) {
        lifecycleScope.launch {  viewModel.delete(noteModel) }
    }

}