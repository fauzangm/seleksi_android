package com.eduside.seleksiandroid.ui.detailItem

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
import com.eduside.seleksiandroid.R
import com.eduside.seleksiandroid.data.local.sp.DataCache
import com.eduside.seleksiandroid.databinding.FragmentDetailBinding
import com.eduside.seleksiandroid.databinding.FragmentDetailFilmBinding
import com.eduside.seleksiandroid.databinding.FragmentDetailShipBinding
import com.eduside.seleksiandroid.databinding.FragmentHomeBinding
import com.eduside.seleksiandroid.ui.DialogGagalGet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailShipFragment : Fragment() {

    private var _binding: FragmentDetailShipBinding? = null
    private val binding get() = _binding!!
    private var id = ""
    private var interrupt = ""
    @Inject
    lateinit var dataCache: DataCache
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailShipBinding.inflate(inflater, container, false)
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

        interrupt = dataCache.dataInterrupt?.interrupt.toString()
        id = dataCache.dataInterrupt?.id.toString()
        viewModel.getShip(id.toInt())
        initAction()
        initObserve()
    }

    private fun initObserve() {
        //ship
        viewModel.getShipError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }

        }
        viewModel.getShipLoading.observe(viewLifecycleOwner) {
        }
        viewModel.getShipResponse.observe(viewLifecycleOwner) {


            binding.tvTitle.text = it.name.toString()
            binding.etModel.text = it.model.toString()
            binding.etManufacturer.text = it.manufacturer.toString()
            binding.etCostCredit.text = it.costInCredits.toString()
            binding.etLenght.text = it.length.toString()
            binding.etmaxspeed.text = it.maxAtmospheringSpeed.toString()
            binding.etCrew.text = it.crew.toString()
            binding.etpassengers.text = it.passengers.toString()
            binding.etCapacity.text = it.cargoCapacity
            binding.etConsumables.text =it.consumables.toString()
            binding.etRating.text = it.hyperdriveRating.toString()
            binding.etMglt.text = it.mGLT.toString()
            binding.etClass.text = it.starshipClass.toString()


            it.pilots?.forEach { vehicle ->
                val lengt = vehicle.length - 2
                var dataID = ""
                if (vehicle[lengt - 1] != '/') {
                    val data = vehicle[lengt]
                    val data2 =vehicle[lengt -1]
                    dataID = data2.toString() + data.toString()
                } else {
                    val data = vehicle[lengt]
                    dataID = data.toString()
                }
                viewModel.getPeople(dataID.toInt())
            }

            it.films?.forEach { vehicle ->
                val lengt = vehicle.length - 2
                var dataID = ""
                if (vehicle[lengt - 1] != '/') {
                    val data = vehicle[lengt]
                    val data2 =vehicle[lengt -1]
                    dataID = data2.toString() + data.toString()
                } else {
                    val data = vehicle[lengt]
                    dataID = data.toString()
                }
                viewModel.getFilm(dataID.toInt())
            }

        }


        //people
        viewModel.getPeopleError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }

        }
        viewModel.getPeopleLoading.observe(viewLifecycleOwner) {
        }
        viewModel.getPeopleResponse.observe(viewLifecycleOwner) {
            binding.etPilots.text = "${binding.etPilots.text}${it.name},"
        }

        //film
        viewModel.getFilmError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }

        }
        viewModel.getFilmLoading.observe(viewLifecycleOwner) {
        }
        viewModel.getFilmResponse.observe(viewLifecycleOwner) {
            binding.etFIlms.text =  "${binding.etFIlms.text}${it.title},"
        }

    }

    private fun initAction() {
        binding.imgback.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailShipFragment_to_navigation_list)
        }
        binding.btnHome.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailShipFragment_to_navigation_home)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}