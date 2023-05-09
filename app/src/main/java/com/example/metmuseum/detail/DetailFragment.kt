package com.example.metmuseum.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        // prevents displaying data before fetching done and returns to id list when object not available
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
           when (isLoading) {
               "LOADING" -> _binding.nestedScrollView.visibility = View.GONE
               "NOT_LOADING" -> _binding.nestedScrollView.visibility = View.VISIBLE
               "ERROR" -> {
                   val navController = findNavController()
                   navController.popBackStack()
                   Toast.makeText(context, "Object currently not available.", LENGTH_LONG).show()
               }
           }
        }

        _binding.objectsGrid.adapter = DetailListAdapter()
        return _binding.root
    }
}