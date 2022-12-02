package com.eduside.seleksiandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.eduside.seleksiandroid.databinding.FragmentGagalGetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogGagalGet : DialogFragment() {

    private var _binding : FragmentGagalGetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this frabg_primary_bottom_big_rounded.xmlgment
        _binding = FragmentGagalGetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}