package com.eduside.seleksiandroid.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eduside.seleksiandroid.R
import com.eduside.seleksiandroid.databinding.ActivityMainBinding
import com.eduside.seleksiandroid.util.showLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewmodel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {
        viewmodel.getPeople()
        viewmodel.getFilm()
        viewmodel.getPlanet()
        viewmodel.getSpecies()
        viewmodel.getShip()
        viewmodel.getVehicle()
        initAction()
        initObserve()
    }

    private fun initObserve() {
        viewmodel.getPeopleError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager, "DialogGagal")

        }
        viewmodel.getPeopleLoading.observe(this) {
        }
        viewmodel.getPeopleResponse.observe(this) {
        }

        viewmodel.getFilmError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager, "DialogGagal")

        }
        viewmodel.getFilmLoading.observe(this) {
        }
        viewmodel.getFilmResponse.observe(this) {
        }

        viewmodel.getPlanetError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager, "DialogGagal")

        }
        viewmodel.getPlanetLoading.observe(this) {
        }
        viewmodel.getPlanetResponse.observe(this) {
        }

        viewmodel.getSpeciesError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager, "DialogGagal")

        }
        viewmodel.getSpeciesLoading.observe(this) {
        }
        viewmodel.getSpeciesResponse.observe(this) {
        }

        viewmodel.getShipError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager, "DialogGagal")

        }
        viewmodel.getShipLoading.observe(this) {
        }
        viewmodel.getShipResponse.observe(this) {
        }

        viewmodel.getVehicleError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager, "DialogGagal")

        }
        viewmodel.getVehicleLoading.observe(this) {
        }
        viewmodel.getVehicleResponse.observe(this) {
        }
    }

    private fun initAction() {

    }
}