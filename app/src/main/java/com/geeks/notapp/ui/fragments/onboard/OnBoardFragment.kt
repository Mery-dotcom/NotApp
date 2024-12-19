package com.geeks.notapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.notapp.R
import com.geeks.notapp.databinding.FragmentOnBoardBinding
import com.geeks.notapp.ui.adapters.OnBoardViewPagerAdapter
import com.geeks.notapp.utils.PreferenceaHelper


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var prefHelper = PreferenceaHelper().apply {
            unit(requireContext())
        }

        if (prefHelper.isOnBoardShown){
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }

        initialize()
        setupListeners()
    }

    private fun initialize() {
        binding.viewpager2.adapter = OnBoardViewPagerAdapter(this)

        binding.dotsIndicator.setViewPager2(binding.viewpager2)

        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }
    }

    private fun setupListeners() = with(binding.viewpager2){
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0,1 -> {
                        binding.button.visibility = View.INVISIBLE
                    }
                    2 -> {
                        binding.button.visibility = View.VISIBLE
                    }
                }
                if (position == 2) {
                    binding.txtSkip.visibility = View.INVISIBLE
                } else{
                    binding.txtSkip.visibility = View.VISIBLE
                    binding.txtSkip.setOnClickListener() {
                            setCurrentItem(currentItem +2, true)
                    }
                }
            }
        })
    }
}