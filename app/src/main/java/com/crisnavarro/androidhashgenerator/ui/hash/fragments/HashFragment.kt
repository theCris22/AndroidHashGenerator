package com.crisnavarro.androidhashgenerator.ui.hash.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.crisnavarro.androidhashgenerator.R
import com.crisnavarro.androidhashgenerator.core.shortSnackBar
import com.crisnavarro.androidhashgenerator.databinding.FragmentHashBinding
import com.crisnavarro.androidhashgenerator.ui.hash.viewmodel.HashViewModel

class HashFragment : Fragment(R.layout.fragment_hash) {

    private val viewModel: HashViewModel by viewModels()
    private val args: HashFragmentArgs by navArgs()
    private var binding: FragmentHashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHashBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpListeners()
    }

    private fun setUpViews() {
        with(binding!!) {
            textViewHash.text = args.hash
        }
    }

    private fun setUpListeners() {
        with(binding!!) {
            buttonCopy.setOnClickListener {
                copyToClipboard(args.hash)
                it.shortSnackBar("Hash copied")
            }
        }
    }

    private fun copyToClipboard(hash: String) {
        val clipManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Hash", hash)
        clipManager.setPrimaryClip(clipData)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}