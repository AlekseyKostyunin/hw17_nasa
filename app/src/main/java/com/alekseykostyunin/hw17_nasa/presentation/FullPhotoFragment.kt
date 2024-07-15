package com.alekseykostyunin.hw17_nasa.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alekseykostyunin.hw17_nasa.R
import com.alekseykostyunin.hw17_nasa.databinding.FragmentFullPhotoBinding
import com.alekseykostyunin.hw17_nasa.presentation.PhotosListFragment.Companion.TAG_URL_PHOTO
import com.bumptech.glide.Glide

class FullPhotoFragment : Fragment() {

    private var _binding: FragmentFullPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullPhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(TAG_URL_PHOTO)
        url?.let {
            Glide
                .with(binding.imageViewFullPhoto.context)
                .load(url)
                .into(binding.imageViewFullPhoto)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}