package com.geeks.notapp.ui.fragments.onboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geeks.notapp.R
import com.geeks.notapp.databinding.FragmentOnBoardPagingBinding


class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding
    companion object{
        const val ARG_ONBOARD_POSITION = "onBoardPosition"
    }

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
    }

    @SuppressLint("SetTextI18n")
    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0->{
                txtTitle.text = "Удобство"
                txtBody.text = "Создавайте заметки в два клика! " +
                        "\nЗаписывайте мысли, идеи и " +
                        "\nважные задачи мгновенно."
                animation.setAnimation(R.raw.first_animation)
//                button.visibility = View.INVISIBLE
            }
            1->{
                txtTitle.text = "Организация"
                txtBody.text = "Организуйте заметки по папкам " +
                        "\nи тегам. Легко находите нужную " +
                        "\nинформацию в любое время."
                animation.setAnimation(R.raw.second_animation)
//                button.visibility = View.INVISIBLE
            }
            2->{
                txtTitle.text = "Синхронизация"
                txtBody.text = "Синхронизация на всех " +
                        "\nустройствах. Доступ к записям в " +
                        "\nлюбое время и в любом месте."
                animation.setAnimation(R.raw.third_animation)
//                button.visibility = View.VISIBLE
            }
        }
    }
}