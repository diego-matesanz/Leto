package com.example.videogamelist.screens.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.videogamelist.R
import com.example.videogamelist.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        initBottomBar()

        return binding.root
    }

    private fun initBottomBar() {
        navHostFragment = childFragmentManager.findFragmentById(R.id.menu_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.menuBottomNavigationView.setupWithNavController(navController)
    }
}
