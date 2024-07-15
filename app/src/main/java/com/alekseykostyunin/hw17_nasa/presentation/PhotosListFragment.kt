package com.alekseykostyunin.hw17_nasa.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.alekseykostyunin.hw17_nasa.R
import com.alekseykostyunin.hw17_nasa.databinding.FragmentPhotosListBinding
import com.alekseykostyunin.hw17_nasa.domain.MyLoadStateAdapter
import com.alekseykostyunin.hw17_nasa.domain.PhotosPagesListAdapter
import com.alekseykostyunin.hw17_nasa.domain.PhotosViewModel
import com.alekseykostyunin.hw17_nasa.entity.Photo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PhotosListFragment : Fragment() {

    private var _binding: FragmentPhotosListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotosViewModel by viewModels()
    private val pagedAdapter = PhotosPagesListAdapter { photo -> onItemClick(photo)  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = pagedAdapter.withLoadStateFooter(MyLoadStateAdapter())
        binding.swipeRefresh.setOnRefreshListener { pagedAdapter.refresh() }
        pagedAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.pagedPhoto.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onItemClick(item: Photo) {
        val bundle = Bundle()
        bundle.putString(TAG_URL_PHOTO, item.imgSrc)
        findNavController().navigate(R.id.action_list_photo_to_full_photo, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG_URL_PHOTO = "TAG_URL_PHOTO"
    }

}

