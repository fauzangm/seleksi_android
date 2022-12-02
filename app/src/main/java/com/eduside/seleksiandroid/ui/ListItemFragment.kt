package com.eduside.seleksiandroid.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduside.seleksiandroid.R
import com.eduside.seleksiandroid.databinding.FragmentHomeBinding
import com.eduside.seleksiandroid.databinding.FragmentListItemBinding
import com.eduside.seleksiandroid.util.showLoading
import dagger.hilt.android.AndroidEntryPoint
import splitties.activities.start
import javax.inject.Inject

@AndroidEntryPoint
class ListItemFragment : Fragment() {

    private var _binding: FragmentListItemBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var adapterPeople : ListPeopleAdapter
    private val viewmodel : ListItemViewModel by viewModels ()
//    @Inject lateinit var adapter: FormPendataanDetailPajakAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListItemBinding.inflate(inflater, container, false)
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
        //adapter People
        binding.rvPeopleItem.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPeopleItem.adapter = adapterPeople

        initAction()
        initObserve()
    }

    private fun initObserve() {
      viewmodel.getPeople().observe(viewLifecycleOwner){
          adapterPeople.submitList(it)
      }
    }

    private fun initAction() {
        binding.imgback.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_navigation_list_to_navigation_home)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}