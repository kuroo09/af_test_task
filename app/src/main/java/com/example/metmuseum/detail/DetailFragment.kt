package com.example.metmuseum.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.navigation.fragment.findNavController
import com.example.metmuseum.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {


    /*
    * TODOs
    * 1-) convert lateinits to lazy
    *
    * */
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    private lateinit var _binding: FragmentDetailBinding

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
               "ERROR" -> displayToast()
           }
        }

        _binding.objectsGrid.adapter = DetailListAdapter()
        return _binding.root
    }

    /**
     * Display Toast when no IDs found for query.
     */
    private fun displayToast() {
        val navController = findNavController()
        navController.popBackStack()
        Toast.makeText(context, "Object currently not available.", LENGTH_LONG).show()
    }
}