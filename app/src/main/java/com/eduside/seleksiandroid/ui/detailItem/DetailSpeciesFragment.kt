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
import com.eduside.seleksiandroid.databinding.FragmentDetailSpeciesBinding
import com.eduside.seleksiandroid.databinding.FragmentHomeBinding
import com.eduside.seleksiandroid.ui.DialogGagalGet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailSpeciesFragment : Fragment() {

    private var _binding: FragmentDetailSpeciesBinding? = null
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
        _binding = FragmentDetailSpeciesBinding.inflate(inflater, container, false)
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
        viewModel.getSpecies(id.toInt())
        initAction()
        initObserve()
    }

    private fun initObserve() {


        //spesies
        viewModel.getSpeciesError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }

        }
        viewModel.getSpeciesLoading.observe(viewLifecycleOwner) {
        }
        viewModel.getSpeciesResponse.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it.name.toString()
            binding.etclassification.text = it.classification.toString()
            binding.etdesignation.text = it.designation.toString()
            binding.etaverageHeight.text = it.averageHeight.toString()
            binding.etskinColors.text = it.skinColors.toString()
            binding.ethairColors.text = it.hairColors.toString()
            binding.eteyeColors.text = it.eyeColors.toString()
            binding.etaverageLifespan.text = it.averageLifespan.toString()
            binding.etlanguage.text = it.language.toString()


            it.people?.forEach { vehicle ->
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

            val lengt = it.homeworld?.length
            var dataID = it.homeworld!![lengt!! -2].toString()
            if (it.homeworld[lengt -3].toString() != "/"){
                val data = it.homeworld[lengt -2].toString()
                val data2 = it.homeworld[lengt -3].toString()
                dataID = data2.toString() + data.toString()
            }
            Log.e("dataworld",dataID)
            viewModel.getPlanet(dataID.toInt())


        }


        //planet
        viewModel.getPlanetError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }

        }
        viewModel.getPlanetLoading.observe(viewLifecycleOwner) {
        }
        viewModel.getPlanetResponse.observe(viewLifecycleOwner) {
            binding.ethomeworld.text = it.name.toString()
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
            binding.etpeople.text = "${binding.etpeople.text}${it.name},"
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
            view?.findNavController()?.navigate(R.id.action_detailSpeciesFragment_to_navigation_list)
        }
        binding.btnHome.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailSpeciesFragment_to_navigation_home)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}