package com.eduside.seleksiandroid.ui.detailItem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.eduside.seleksiandroid.R
import com.eduside.seleksiandroid.data.local.sp.DataCache
import com.eduside.seleksiandroid.databinding.FragmentDetailBinding
import com.eduside.seleksiandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var id =""
    @Inject lateinit var dataCache: DataCache
//    private val viewModel : FormPendataanViewModel by viewModels ()
//    @Inject lateinit var adapter: FormPendataanDetailPajakAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

        id = dataCache.getString(DataCache.ID).toString()
        binding.tvTitle.text = id
        initAction()
    }

    private fun initAction() {
        binding.imgback.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailFragment_to_navigation_list)
        }
        binding.btnHome.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailFragment_to_navigation_home)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}