package com.dmatesanz.leto.screens.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dmatesanz.leto.R
import com.dmatesanz.leto.databinding.FragmentMenuBinding
import com.google.firebase.auth.FirebaseAuth

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        checkUserLogged()
        initBottomBar()

        return binding.root
    }

    private fun checkUserLogged() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            findNavController().navigate(R.id.action_menuFragment_to_loginFragment)
        }
    }

    private fun initBottomBar() {
        navHostFragment = childFragmentManager.findFragmentById(R.id.menu_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.menuBottomNavigationView.setupWithNavController(navController)
    }
}
