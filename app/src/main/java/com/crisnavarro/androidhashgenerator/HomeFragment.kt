package com.crisnavarro.androidhashgenerator

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.crisnavarro.androidhashgenerator.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home), MenuProvider {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
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

    private fun initViews() {
        val array = resources.getStringArray(R.array.hash_algorithms)
        ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, array).apply {
            binding.acHash.setAdapter(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}