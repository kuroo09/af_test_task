package com.example.search_ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.isInvisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.functionality.shared.data.met_api.entities.Result
import com.example.functionality.shared.data.met_api.entities.SearchCollectionDto
import com.example.search_ui.databinding.FragmentSearchBinding
import com.example.search_ui.state.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var _binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater)

        _binding.lifecycleOwner = this

        // Handle SearchView actions.
        handleSearchField()

        observeResultData()

        _binding.objectsList.adapter = ObjectListAdapter()
        return _binding.root
    }

    private fun observeResultData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dataFlow.collect { result ->
                    when (result) {
                        Result.Error -> displayToast()
                        Result.Loading -> _binding.loadingView.visibility = View.VISIBLE
                        is Result.Success -> applyUiModel(result.data)
                    }
                }
            }
        }
    }

    private fun displayToast() {
        _binding.loadingView.visibility = View.GONE
        _binding.objectsList.isInvisible = true
        Toast.makeText(context, "No objects found.", LENGTH_SHORT).show()
    }

    private fun applyUiModel(result: SearchCollectionDto) {
        _binding.apply {
            viewModel = result
            loadingView.visibility = View.GONE
            objectsList.isInvisible = false
            objectsList.clearFocus()
        }
    }

    private fun handleSearchField() {
        _binding.searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { viewModel.onSearchSubmit(p0) }
                // hide keyboard on submit
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(_binding.searchField.windowToken, 0)
                _binding.searchField.clearFocus()   // does not prevents double execution of method on Enter key
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }
}