package com.example.noteappforpatern.ui.fragments.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappforpatern.R
import com.example.noteappforpatern.data.local.entity.NoteEntity
import com.example.noteappforpatern.databinding.FragmentDetailBinding
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailFragment : Fragment(), DetailViewInterface {

    private val presenter: DetailPresenterInterface by inject()
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private var timeText = ""
    private var dateText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dates()
        setupTextChangedListener()
        checkButtonVisibility()
        initListener()

        // Retrieve the note details if an ID is passed
        args.id.let { id -> presenter.getNoteById(id) }
    }

    private fun dates() {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
        dateText = dateFormat.format(currentDate)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        timeText = timeFormat.format(currentDate)
        binding.txtTime.text = timeText
        binding.txtDate.text = dateText
    }

    private fun initListener() {
        binding.btnFinished.setOnClickListener {
            val noteModel = NoteEntity(
                title = binding.etTitle.text.toString(),
                content = binding.etDescription.text.toString(),
                time = timeText,
                date = dateText
            )
            presenter.saveNote(noteModel)
        }
    }

    private fun setupTextChangedListener() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })

        binding.etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })
    }

    private fun checkButtonVisibility() {
        val titleText = binding.etTitle.text.toString().trim()
        val descriptionText = binding.etDescription.text.toString().trim()
        binding.btnFinished.visibility =
            if (titleText.isNotEmpty() && descriptionText.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }


    override fun showNoteDetails(noteEntity: LiveData<NoteEntity>) {
        noteEntity.observe(viewLifecycleOwner) {
            binding.apply {
                etTitle.setText(it.title)
                etDescription.setText(it.content)
                txtTime.text = it.time
                txtDate.text = it.date
            }
        }
    }

    override fun navigateToNoteFragment() {
        findNavController().navigate(R.id.noteFragment)
    }
}
