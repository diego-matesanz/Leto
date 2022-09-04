package com.example.videogamelist.screens.menu.bookmarked

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.videogamelist.databinding.FragmentBookmarkedBinding
import com.example.videogamelist.screens.menu.MenuViewModel

class BookmarkedFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkedBinding

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkedBinding.inflate(layoutInflater)

        return binding.root
    }
}
