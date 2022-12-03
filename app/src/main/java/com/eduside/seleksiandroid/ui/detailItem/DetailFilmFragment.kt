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
import com.eduside.seleksiandroid.databinding.FragmentHomeBinding
import com.eduside.seleksiandroid.ui.DialogGagalGet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFilmFragment : Fragment() {

    private var _binding: FragmentDetailFilmBinding? = null
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
        _binding = FragmentDetailFilmBinding.inflate(inflater, container, false)
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
        viewModel.getFilm(id.toInt())
        initAction()
        initObserve()
    }

    private fun initObserve() {

        //vehicles
        viewModel.getVehicleError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }

        }
        viewModel.getVehicleLoading.observe(viewLifecycleOwner) {
        }
        viewModel.getVehicleResponse.observe(viewLifecycleOwner) {
            binding.etVehicles.text = "${binding.etVehicles.text}${it.name},"

        }

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
            binding.etspecies.text = "${binding.etspecies.text}${it.name},"

        }

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
            binding.etStartShip.text = "${binding.etStartShip.text}${it.name},"

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
            binding.etPlanet.text = "${binding.etPlanet.text}${it.name},"

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
            binding.etCharacters.text = "${binding.etCharacters.text}${it.name},"
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
            binding.tvTitle.text = it.title.toString()
            binding.etEpisode.text = it.episodeId.toString()
            binding.etCrawl.text = it.openingCrawl.toString()
            binding.etDirector.text = it.director.toString()
            binding.etRelase.text = it.releaseDate.toString()
            binding.etProducer.text = it.producer.toString()


            it.characters?.forEach { vehicle ->
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

            it.vehicles?.forEach { vehicle ->
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
                viewModel.getVehicle(dataID.toInt())
            }

            it.species?.forEach { vehicle ->
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
                viewModel.getSpecies(dataID.toInt())
            }

            it.starships?.forEach { vehicle ->
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
                viewModel.getShip(dataID.toInt())
            }


            it.planets?.forEach { vehicle ->
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
                viewModel.getPlanet(dataID.toInt())
            }



        }

    }

    private fun initAction() {
        binding.imgback.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailFilmFragment_to_navigation_list)
        }
        binding.btnHome.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailFilmFragment_to_navigation_home)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}