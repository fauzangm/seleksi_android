package com.eduside.seleksiandroid.ui.listItem

import androidx.lifecycle.*
import com.eduside.seleksiandroid.data.local.db.entities.*
import com.eduside.seleksiandroid.data.repository.film.GetFilmRepository
import com.eduside.seleksiandroid.data.repository.people.GetPeopleRepository
import com.eduside.seleksiandroid.data.repository.people.GetPeopleResult
import com.eduside.seleksiandroid.data.repository.planet.GetPlanetRepository
import com.eduside.seleksiandroid.data.repository.species.GetSpeciesRepository
import com.eduside.seleksiandroid.data.repository.startship.GetStartshipRepository
import com.eduside.seleksiandroid.data.repository.vehicle.GetVehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemViewModel @Inject constructor(
    private val peopleRepository: GetPeopleRepository,
    private val filmRepository: GetFilmRepository,
    private val planetRepository: GetPlanetRepository,
    private val speciesRepository: GetSpeciesRepository,
    private val vehicleRepository: GetVehicleRepository,
    private val startshipRepository: GetStartshipRepository,
) : ViewModel() {


   val readPeople = peopleRepository.itemPeople
    fun getPeople() {
        viewModelScope.launch {
            peopleRepository.getPeople()
        }
    }

    val readFIlm = filmRepository.filmVoItem
    fun getFilm() {
        viewModelScope.launch {
            filmRepository.getFilm()
        }
    }
    val readPlanet = planetRepository.planetVoItem
    fun getPlanet(){
        viewModelScope.launch {
            planetRepository.getItemPlanet()
        }
    }
    val readSpecies = speciesRepository.speciesVoItem
    fun getSpecies(){
        viewModelScope.launch {
            speciesRepository.getItemSpecies()
        }
    }

    val readVehicle = vehicleRepository.vehicleItemVo
    fun getVehicle(){
        viewModelScope.launch {
            vehicleRepository.getItemVehicle()
        }
    }

    val raeadship = startshipRepository.shipVoItem
    fun getShip() {
        viewModelScope.launch {
            startshipRepository.getItemShip()
        }
    }

    fun clearFilm(){
        viewModelScope.launch {
            filmRepository.clearFilm()
        }
    }


}