package com.eduside.seleksiandroid.ui.listItem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduside.seleksiandroid.R
import com.eduside.seleksiandroid.data.local.sp.DataCache
import com.eduside.seleksiandroid.data.local.sp.FormatDataInterrupt
import com.eduside.seleksiandroid.databinding.FragmentListItemBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class ListItemFragment : Fragment() {

    private var _binding: FragmentListItemBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapterPeople: ListPeopleAdapter

    @Inject
    lateinit var adapterFIlm: ListFilmAdapter

    @Inject
    lateinit var adapterPlanet: ListPlanetAdapter

    @Inject
    lateinit var adapterSpeices: ListSpeciesAdapter

    @Inject
    lateinit var adapterShip: ListShipAdapter

    @Inject
    lateinit var adapterVehicle: ListVehicleAdapter

    @Inject
    lateinit var dataCache: DataCache
    private val viewmodel: ListItemViewModel by viewModels()
    private var interrupt = ""
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
        interrupt = dataCache.dataInterrupt?.interrupt.toString()
        Log.e("interr", interrupt)
        //adapter People
        binding.rvPeopleItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPeopleItem.adapter = adapterPeople


        //adapter Vehicle
        binding.rvVehiclesItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvVehiclesItem.adapter = adapterVehicle

        //adapter Ship
        binding.rvShipItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvShipItem.adapter = adapterShip

        //adapter Species
        binding.rvSpeciesItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSpeciesItem.adapter = adapterSpeices

        //adapter Film
        binding.rvFilmItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFilmItem.adapter = adapterFIlm

        //adapter Planet
        binding.rvPlanetItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPlanetItem.adapter = adapterPlanet

        initAction()
        initObserve()

    }

    private fun initObserve() {
        if (interrupt == "1") {
            viewmodel.getPeople()
            Log.e("interr", "masukinterrup")
            binding.rvPeopleItem.visibility = View.VISIBLE
            viewmodel.readPeople.observe(viewLifecycleOwner) {
                Log.e("itempeople", it.toString())
                adapterPeople.submitList(it)
            }

        }

        if (interrupt == "2") {
            viewmodel.getFilm()
            binding.rvFilmItem.visibility = View.VISIBLE
            viewmodel.readFIlm.observe(viewLifecycleOwner) {
                adapterFIlm.submitList(it)
            }

        }

        if (interrupt == "3") {
            viewmodel.getPlanet()
            binding.rvPlanetItem.visibility = View.VISIBLE
            viewmodel.readPlanet.observe(viewLifecycleOwner) {
                adapterPlanet.submitList(it)
            }
        }

        if (interrupt == "4") {
            viewmodel.getSpecies()
            binding.rvSpeciesItem.visibility = View.VISIBLE
            viewmodel.readSpecies.observe(viewLifecycleOwner) {
                adapterSpeices.submitList(it)
            }
        }

        if (interrupt == "5") {
            viewmodel.getVehicle()
            binding.rvVehiclesItem.visibility = View.VISIBLE
            viewmodel.readVehicle.observe(viewLifecycleOwner) {
                adapterVehicle.submitList(it)
            }
        }

        if (interrupt == "6") {
            viewmodel.getShip()
            binding.rvShipItem.visibility = View.VISIBLE
            viewmodel.raeadship.observe(viewLifecycleOwner) {
                adapterShip.submitList(it)
            }
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

    @Subscribe
    fun onItemClickedEvent(event: ItemDataPeopleEvent) {
        dataCache.dataInterrupt = FormatDataInterrupt(
            id = event.data.id.toString(),
            interrupt = dataCache.dataInterrupt?.interrupt
        )
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_detailFragment)
    }

    @Subscribe
    fun onItemClickedFIlmEvent(event: ItemDataFilmEvent) {
        dataCache.dataInterrupt = FormatDataInterrupt(
            id = event.data.id.toString(),
            interrupt = dataCache.dataInterrupt?.interrupt
        )
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_detailFilmFragment)
    }

    @Subscribe
    fun onItemClickedPlanetEvent(event: ItemDataPlanetEvent) {
        dataCache.dataInterrupt = FormatDataInterrupt(
            id = event.data.id.toString(),
            interrupt = dataCache.dataInterrupt?.interrupt
        )
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_detailPlanetFragment)
    }

    @Subscribe
    fun onItemClickedShipEvent(event: ItemDataShipEvent) {
        dataCache.dataInterrupt = FormatDataInterrupt(
            id = event.data.id.toString(),
            interrupt = dataCache.dataInterrupt?.interrupt
        )
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_detailShipFragment)
    }

    @Subscribe
    fun onItemClickedSpeciesEvent(event: ItemDataSpeciesEvent) {
        dataCache.dataInterrupt = FormatDataInterrupt(
            id = event.data.id.toString(),
            interrupt = dataCache.dataInterrupt?.interrupt
        )
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_detailSpeciesFragment)
    }

    @Subscribe
    fun onItemClickedVehicleEvent(event: ItemDataVehicleEvent) {
        dataCache.dataInterrupt = FormatDataInterrupt(
            id = event.data.id.toString(),
            interrupt = dataCache.dataInterrupt?.interrupt
        )
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_detailVehicleFragment)
    }



    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }




}