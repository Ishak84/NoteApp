package com.geeks.noteapp.ui.fragments.note

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentNoteDetailBinding
import com.geeks.noteapp.date.model.NoteModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var selectedColor: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        date()


        binding.colorSelectionRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnColorGray -> selectedColor = Color.parseColor("#191818")
                R.id.btnColorWhite -> selectedColor = Color.parseColor("#EBE4C9")
                R.id.btnColorBrown -> selectedColor = Color.parseColor("#571818")
                else -> selectedColor = Color.BLACK
            }
        }
    }

    private fun date() {
        val currentDate =
            SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        binding.tvTime.text = currentTime
        binding.tvDate.text = currentDate

        binding.imgReturn.setOnClickListener {
            findNavController().navigate(NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteFragment())
        }
    }

    private fun setupListener() {
        binding.tvSend.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etText.text.toString()
            val time = binding.tvTime.text.toString()
            val date = binding.tvDate.text.toString()
            val color = if (selectedColor != 0) "#" + Integer.toHexString(selectedColor)
                .substring(2) else "#000000"

            App().getInstance()?.noteDao()?.insertNote(
                NoteModel(
                    title, description, time, date, color
                )
            )
            findNavController().navigateUp()
        }
    }

}