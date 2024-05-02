package com.geeks.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentOnBoardPagingBinding
import com.geeks.noteapp.date.Pref
import com.geeks.noteapp.ui.adapter.OnBoardViewPagerAdapter


class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        if(requireArguments().getInt(ARG_ON_BOARD_POSITION)==2){
            Pref(requireContext(),).isOnBoardShown=true
        }
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ON_BOARD_POSITION)) {
            0 -> {
                onBoardTxt.text = "Очень удобный функционал"
                lottie.setAnimation(R.raw.lottie1)
            }

            1 -> {
                onBoardTxt.text = "Быстрый кaчественный продукт"
                lottie.setAnimation(R.raw.lottie2)
            }

            2 -> {
                onBoardTxt.text = "Куча функций и интересных фишек"
                lottie.setAnimation(R.raw.lottie3)

            }
        }
    }

    companion object {
        const val ARG_ON_BOARD_POSITION = "onBoard"
    }
}

