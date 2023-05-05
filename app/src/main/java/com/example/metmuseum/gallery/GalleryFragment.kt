package com.example.metmuseum.gallery

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import com.example.metmuseum.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var _binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater)

        _binding.lifecycleOwner = this

        _binding.viewModel = viewModel

        _binding.searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { viewModel.searchObjects(p0, context!!) }
                // hide keyboard on submit
                val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(_binding.searchField.windowToken, 0)
                //_binding.searchField.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        _binding.objectsList.adapter = ObjectListAdapter()
        return _binding.root
    }

}