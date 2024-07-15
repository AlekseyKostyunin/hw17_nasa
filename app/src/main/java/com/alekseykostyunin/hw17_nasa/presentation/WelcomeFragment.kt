package com.alekseykostyunin.hw17_nasa.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alekseykostyunin.hw17_nasa.R
import com.alekseykostyunin.hw17_nasa.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private  var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.welcome.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_photo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}