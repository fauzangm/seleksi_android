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
import androidx.navigation.findNavController
import com.eduside.seleksiandroid.R
import com.eduside.seleksiandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
//    private val viewModel : FormPendataanViewModel by viewModels ()
//    @Inject lateinit var adapter: FormPendataanDetailPajakAdapter

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
        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {
        //adapte

        initAction()
    }

    private fun initAction() {
//        binding.ivBack.setOnClickListener {
//            view?.findNavController()?.navigate(R.id.action_navigation_pendataan_detail_objek_to_navigation_pendataan_objek_pajak)
//        }
        binding.btnPeople.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_navigation_home_to_navigation_list)
        }
//
//
//        binding.btnKlasifikasi.setOnClickListener{
//            val bodyDialogFragment = BottomDialogDetailPajak()
//            activity?.supportFragmentManager?.let { it1 -> bodyDialogFragment.show(it1, "DialogDetailPajak") }
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}