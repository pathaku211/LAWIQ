package com.example.lawiq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lawiq.R
import com.example.lawiq.databinding.FragmentSectionsBinding // Ensure this import is correct

class SectionsFragment : Fragment() {
    private var _binding: FragmentSectionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout and get the binding instance
        _binding = FragmentSectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.partV.setOnClickListener {
            // Navigate to ArticlesFragment with Part V data
            val action = SectionsFragmentDirections.actionSectionsToArticlesFragment("Part V")
            findNavController().navigate(action)
        }

        binding.partVI.setOnClickListener {
            // Navigate to ArticlesFragment with Part VI data
            val action = SectionsFragmentDirections.actionSectionsToArticlesFragment("Part VI")
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clean up binding reference to prevent memory leaks
    }
}

class SectionsFragmentDirections {
    companion object {
        fun actionSectionsToArticlesFragment(s: String): Any {
            TODO("Not yet implemented")
        }
    }

}
