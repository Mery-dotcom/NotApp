package com.geeks.notapp.ui.fragments.splashScreen

import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geeks.notapp.R
import com.geeks.notapp.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.util.logging.Handler

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.splashScreen.setAnimation(R.raw.splash_screen)
        binding.splashScreen.playAnimation()

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null){
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeScreenFragment)
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardPagingFragment)
            }
        }, 3000)
    }
}