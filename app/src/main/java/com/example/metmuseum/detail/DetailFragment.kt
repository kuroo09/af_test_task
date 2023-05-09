package com.example.metmuseum.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.metmuseum.R
import com.example.metmuseum.databinding.FragmentDetailBinding
import com.example.metmuseum.databinding.FragmentGalleryBinding
import com.example.metmuseum.gallery.GalleryViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    private lateinit var _binding: FragmentDetailBinding

    val metArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)

        _binding.lifecycleOwner = this

        viewModelFactory = DetailViewModelFactory(DetailFragmentArgs.fromBundle(requireArguments()).metId)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        _binding.detailViewModel = viewModel

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
           if (isLoading) {
               _binding.nestedScrollView.visibility = View.GONE
           } else {
               _binding.nestedScrollView.visibility = View.VISIBLE
           }
        }

        _binding.objectsGrid.adapter = DetailListAdapter()
        return _binding.root
    }




}