package com.geeks.noteapp.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.date.model.NoteModel
import com.geeks.noteapp.interfaces.OnClickItem
import com.geeks.noteapp.ui.adapter.NoteAdapter

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)
    private var isGridLayout = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCharge.setOnClickListener {
            isGridLayout = !isGridLayout
            changeRecyclerViewLayout()
        }
        setupListener()
        initialize()
        getData()
    }

    override fun onResume() {
        super.onResume()
    }


    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    private fun initialize() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() = with(binding) {
        btnPlus.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        btnCharge.setOnClickListener {
            isGridLayout = !isGridLayout
            val layoutManager =
                if (isGridLayout) {
                    GridLayoutManager(requireContext(), 2)
                } else {
                    LinearLayoutManager(requireContext())
                }
            binding.rvHome.layoutManager = layoutManager
        }
    }

    private fun changeRecyclerViewLayout() {
        val layoutManager = if (isGridLayout) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }

        binding.rvHome.layoutManager = layoutManager
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить")
            setPositiveButton("Да") { dialog, which ->
                App.appDataBase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}