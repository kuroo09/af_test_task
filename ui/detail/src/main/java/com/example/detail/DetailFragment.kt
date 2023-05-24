package com.example.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.met_api.Result

@AndroidEntryPoint
class DetailFragment : Fragment() {
    /*
    * TODOs
    * 1-) convert lateinits to lazy -> DONE
    * Questions: lazy init of _binding?
    * */

    private val viewModel: com.example.detail.state.DetailViewModel by viewModels()

    private lateinit var _binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)

        _binding.lifecycleOwner = this

        observeResultData()

        _binding.objectsGrid.adapter = DetailListAdapter()
        return _binding.root
    }

    private fun observeResultData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.result.collect { result ->
                    when (result) {
                        Result.Error -> displayToast()
                        Result.Loading -> _binding.nestedScrollView.visibility = View.GONE
                        is Result.Success -> {
                            applyUiModel(result.data)
                        }
                    }
                }
            }
        }
    }

    /**
     * Apply result to uiModel instead of ViewModel and display data.
     */
    private fun applyUiModel(result: com.example.entities.DetailUiModel) {
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