package com.geeks.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentOnBoardBinding
import com.geeks.noteapp.databinding.FragmentOnBoardPagingBinding
import com.geeks.noteapp.date.Pref
import com.geeks.noteapp.ui.adapter.OnBoardViewPagerAdapter
import java.text.FieldPosition


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var pref : Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        pref = Pref (requireContext())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!pref.isOnBoardShown) {
            initialize()
            setupListener()
        }else{
            findNavController().navigate(OnBoardFragmentDirections.actionOnBoardFragmentToNoteFragment())
        }
    }


    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
    }


    private fun setupListener() = with(binding.viewPager) {
        binding.nextTxt.setOnClickListener {
            if (currentItem < 3) {

                setCurrentItem(currentItem + 2, true)
            }
        }
        binding.sendTxt.setOnClickListener {
            pref.isOnBoardShown = true
            findNavController().navigate(R.id.noteFragment)

        }
    }

    private fun btnGetStarted() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        nextTxt.isVisible = true
                        sendTxt.isVisible = false
                    }

                    1 -> {
                        nextTxt.isVisible = true
                        sendTxt.isVisible = false
                    }

                    2 -> {
                        nextTxt.isVisible = false
                        sendTxt.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
}
