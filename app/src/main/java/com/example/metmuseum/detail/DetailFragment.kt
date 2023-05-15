package com.example.metmuseum.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.metmuseum.databinding.FragmentDetailBinding
import com.example.metmuseum.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    /*
    * TODOs
    * 1-) convert lateinits to lazy -> DONE
    * Questions: lazy init of _binding?
    * */

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var _binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)

        _binding.lifecycleOwner = this

        viewModel.result.observe(viewLifecycleOwner) { result ->
            when (result) {
                Result.Error -> displayToast()
                Result.Loading -> _binding.nestedScrollView.visibility = View.GONE
                is Result.Success -> {
                    applyUiModel(result.data)
                }
            }
        }

        _binding.objectsGrid.adapter = DetailListAdapter()
        return _binding.root
    }

    /**
     * Apply result to uiModel instead of ViewModel and display data.
     */
    private fun applyUiModel(result: DetailUiModel) {
        _binding.uiModel = result
        _binding.nestedScrollView.visibility = View.VISIBLE
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