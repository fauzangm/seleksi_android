package com.eduside.seleksiandroid.ui

import androidx.lifecycle.*
import com.eduside.seleksiandroid.data.remote.response.GetSpeciesResponse
import com.eduside.seleksiandroid.data.repository.film.GetFilmRepository
import com.eduside.seleksiandroid.data.repository.film.GetFilmResult
import com.eduside.seleksiandroid.data.repository.people.GetPeopleRepository
import com.eduside.seleksiandroid.data.repository.people.GetPeopleResult
import com.eduside.seleksiandroid.data.repository.planet.GetPlanetRepository
import com.eduside.seleksiandroid.data.repository.planet.GetPlanetResult
import com.eduside.seleksiandroid.data.repository.species.GetSpeciesRepository
import com.eduside.seleksiandroid.data.repository.species.GetSpeciesResult
import com.eduside.seleksiandroid.data.repository.startship.GetStartshipRepository
import com.eduside.seleksiandroid.data.repository.startship.GetStartshipResult
import com.eduside.seleksiandroid.data.repository.vehicle.GetVehicleRepository
import com.eduside.seleksiandroid.data.repository.vehicle.GetVehicleResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val peopleRepository: GetPeopleRepository,
    private val filmRepository: GetFilmRepository,
    private val planetRepository: GetPlanetRepository,
    private val speciesRepository: GetSpeciesRepository,
    private val vehicleRepository: GetVehicleRepository,
    private val startshipRepository: GetStartshipRepository
) : ViewModel() {

    private val getPeople = MutableLiveData<GetPeopleResult>()
    val getPeopleError = Transformations.switchMap(getPeople) { it.error }
    val getPeopleLoading = Transformations.switchMap(getPeople) { it.loading }
    val getPeopleResponse = Transformations.switchMap(getPeople) { it.listItem }
    fun getPeople() {
        viewModelScope.launch {
            getPeople.postValue(peopleRepository.getPeopleItem())
        }
    }

    private val getFilmResult = MutableLiveData<GetFilmResult>()
    val getFilmError = Transformations.switchMap(getFilmResult) { it.error }
    val getFilmLoading = Transformations.switchMap(getFilmResult) { it.loading }
    val getFilmResponse = Transformations.switchMap(getFilmResult) { it.listItem }
    fun getFilm() {
        viewModelScope.launch {
            getFilmResult.postValue(filmRepository.getFilmItem())
        }
    }

    private val getPlanetResult = MutableLiveData<GetPlanetResult>()
    val getPlanetError = Transformations.switchMap(getPlanetResult) { it.error }
    val getPlanetLoading = Transformations.switchMap(getPlanetResult) { it.loading }
    val getPlanetResponse = Transformations.switchMap(getPlanetResult) { it.listItem }
    fun getPlanet() {
        viewModelScope.launch {
            getPlanetResult.postValue(planetRepository.getPlanet())
        }
    }

    private val getSpeciesResult = MutableLiveData<GetSpeciesResult>()
    val getSpeciesError = Transformations.switchMap(getSpeciesResult) { it.error }
    val getSpeciesLoading = Transformations.switchMap(getSpeciesResult) { it.loading }
    val getSpeciesResponse = Transformations.switchMap(getSpeciesResult) { it.listItem }
    fun getSpecies() {
        viewModelScope.launch {
            getSpeciesResult.postValue(speciesRepository.geteSPecies())
        }
    }


    private val getShipResult = MutableLiveData<GetStartshipResult>()
    val getShipError = Transformations.switchMap(getShipResult) { it.error }
    val getShipLoading = Transformations.switchMap(getShipResult) { it.loading }
    val getShipResponse = Transformations.switchMap(getShipResult) { it.listItem }
    fun getShip() {
        viewModelScope.launch {
            getShipResult.postValue(startshipRepository.getStartship())
        }
    }

    private val getVehicleResult = MutableLiveData<GetVehicleResult>()
    val getVehicleError = Transformations.switchMap(getVehicleResult) { it.error }
    val getVehicleLoading = Transformations.switchMap(getVehicleResult) { it.loading }
    val getVehicleResponse = Transformations.switchMap(getVehicleResult) { it.listItem }
    fun getVehicle() {
        viewModelScope.launch {
            getVehicleResult.postValue(vehicleRepository.getVehicle())
        }
    }

}