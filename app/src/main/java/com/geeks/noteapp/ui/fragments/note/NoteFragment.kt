package com.geeks.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.date.Pref
import com.geeks.noteapp.date.model.NoteModel
import com.geeks.noteapp.ui.adapter.NoteAdapter

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private var isGridLayout = false
    private val noteAdapter = NoteAdapter()
    private val list: ArrayList<NoteModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGridLy.setOnClickListener {
            isGridLayout = !isGridLayout
            changeRecyclerViewLayout()
        }
        setupListener()
        initialize()
        getData()


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

    private fun setupListener() {
        binding.btnPlus.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
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


}