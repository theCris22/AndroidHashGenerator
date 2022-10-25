package com.crisnavarro.androidhashgenerator.ui.home.fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.crisnavarro.androidhashgenerator.R
import com.crisnavarro.androidhashgenerator.core.shortSnackBar
import com.crisnavarro.androidhashgenerator.databinding.FragmentHomeBinding
import com.crisnavarro.androidhashgenerator.ui.home.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home), MenuProvider {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onStart() {
        super.onStart()
        setUpViews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpListeners()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.home_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.clear_text_menu -> {
                true
            }
            else -> false
        }
    }

    private fun setUpViews() {

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.hash_algorithms)
        ).apply {
            binding!!.acHash.setAdapter(this)
        }

    }

    private fun setUpListeners() {
        with(binding!!) {
            btnGenerateHash.setOnClickListener {
                if (isValidFrom())
                    goToHash()
                else
                    btnGenerateHash.shortSnackBar("You have to write something.") { editTextHash.requestFocus() }
            }
        }
    }

    private fun goToHash() {
        lifecycleScope.launch {
            animation()
            delay(400)
            findNavController().navigate(R.id.action_homeFragment_to_hashFragment)
        }
    }

    private fun isValidFrom() = binding!!.editTextHash.text.isNotEmpty()

    private fun animation() {
        with(binding!!) {
            textViewTittle.animate().alpha(0f).translationYBy(1200f).duration = 400L
            textInputLayout.animate().alpha(0f).translationXBy(1200f).duration = 400L
            editTextHash.animate().alpha(0f).translationXBy(-1200f).duration = 400L
            btnGenerateHash.animate().alpha(0f).translationYBy(-1200f).duration = 400L
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}