package com.eduside.seleksiandroid.ui.detailItem

import androidx.lifecycle.*
import com.eduside.seleksiandroid.data.local.db.entities.*
import com.eduside.seleksiandroid.data.remote.response.GetSpeciesResponse
import com.eduside.seleksiandroid.data.repository.film.GetDetailFilmResult
import com.eduside.seleksiandroid.data.repository.film.GetFilmRepository
import com.eduside.seleksiandroid.data.repository.film.GetFilmResult
import com.eduside.seleksiandroid.data.repository.people.GetDetailPeopleResult
import com.eduside.seleksiandroid.data.repository.people.GetPeopleRepository
import com.eduside.seleksiandroid.data.repository.people.GetPeopleResult
import com.eduside.seleksiandroid.data.repository.planet.GetDetailPlanetResult
import com.eduside.seleksiandroid.data.repository.planet.GetPlanetRepository
import com.eduside.seleksiandroid.data.repository.planet.GetPlanetResult
import com.eduside.seleksiandroid.data.repository.species.GetDetailSpeciesResult
import com.eduside.seleksiandroid.data.repository.species.GetSpeciesRepository
import com.eduside.seleksiandroid.data.repository.species.GetSpeciesResult
import com.eduside.seleksiandroid.data.repository.startship.GetDetailStartshipResult
import com.eduside.seleksiandroid.data.repository.startship.GetStartshipRepository
import com.eduside.seleksiandroid.data.repository.startship.GetStartshipResult
import com.eduside.seleksiandroid.data.repository.vehicle.GetDetailVehicleResult
import com.eduside.seleksiandroid.data.repository.vehicle.GetVehicleRepository
import com.eduside.seleksiandroid.data.repository.vehicle.GetVehicleResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val peopleRepository: GetPeopleRepository,
    private val filmRepository: GetFilmRepository,
    private val planetRepository: GetPlanetRepository,
    private val speciesRepository: GetSpeciesRepository,
    private val vehicleRepository: GetVehicleRepository,
    private val startshipRepository: GetStartshipRepository
) : ViewModel() {

    fun getFIlm(searchQuery: String): LiveData<List<FilmVo>> {
        return filmRepository.getFIlm(searchQuery).asLiveData()
    }

//    fun getPlanet(searchQuery: String): LiveData<List<PlanetVo>> {
//        return planetRepository.getPlanet(searchQuery).asLiveData()
//    }
//
//    fun getPeople(searchQuery: String): LiveData<List<PeopleVo>> {
//        return peopleRepository.getPeople(searchQuery).asLiveData()
//    }
//
//    fun getSpecies(searchQuery: String): LiveData<List<SpeciesVo>> {
//        return speciesRepository.getSpecies(searchQuery).asLiveData()
//    }
//    fun getVehicle(searchQuery: String): LiveData<List<VehiclesVo>> {
//        return vehicleRepository.getVehicle(searchQuery).asLiveData()
//    }
//    fun getShip(searchQuery: String): LiveData<List<ShipVo>> {
//        return startshipRepository.getShip(searchQuery).asLiveData()
//    }
    private val getPeople = MutableLiveData<GetDetailPeopleResult>()
    val getPeopleError = Transformations.switchMap(getPeople) { it.error }
    val getPeopleLoading = Transformations.switchMap(getPeople) { it.loading }
    val getPeopleResponse = Transformations.switchMap(getPeople) { it.listItem }
    fun getPeople(id:Int) {
        viewModelScope.launch {
            getPeople.postValue(peopleRepository.getPeopleDetailItem(id))
        }
    }

    private val getFilmResult = MutableLiveData<GetDetailFilmResult>()
    val getFilmError = Transformations.switchMap(getFilmResult) { it.error }
    val getFilmLoading = Transformations.switchMap(getFilmResult) { it.loading }
    val getFilmResponse = Transformations.switchMap(getFilmResult) { it.listItem }
    fun getFilm(id:Int) {
        viewModelScope.launch {
            getFilmResult.postValue(filmRepository.getDetailFilmItem(id))
        }
    }

    private val getPlanetResult = MutableLiveData<GetDetailPlanetResult>()
    val getPlanetError = Transformations.switchMap(getPlanetResult) { it.error }
    val getPlanetLoading = Transformations.switchMap(getPlanetResult) { it.loading }
    val getPlanetResponse = Transformations.switchMap(getPlanetResult) { it.listItem }
    fun getPlanet(id:Int) {
        viewModelScope.launch {
            getPlanetResult.postValue(planetRepository.getDetailPlanetItem(id))
        }
    }

    private val getSpeciesResult = MutableLiveData<GetDetailSpeciesResult>()
    val getSpeciesError = Transformations.switchMap(getSpeciesResult) { it.error }
    val getSpeciesLoading = Transformations.switchMap(getSpeciesResult) { it.loading }
    val getSpeciesResponse = Transformations.switchMap(getSpeciesResult) { it.listItem }
    fun getSpecies(id:Int) {
        viewModelScope.launch {
            getSpeciesResult.postValue(speciesRepository.getDetailHumanItem(id))
        }
    }


    private val getShipResult = MutableLiveData<GetDetailStartshipResult>()
    val getShipError = Transformations.switchMap(getShipResult) { it.error }
    val getShipLoading = Transformations.switchMap(getShipResult) { it.loading }
    val getShipResponse = Transformations.switchMap(getShipResult) { it.listItem }
    fun getShip(id:Int) {
        viewModelScope.launch {
            getShipResult.postValue(startshipRepository.getDetaiShipItem(id))
        }
    }

    private val getVehicleResult = MutableLiveData<GetDetailVehicleResult>()
    val getVehicleError = Transformations.switchMap(getVehicleResult) { it.error }
    val getVehicleLoading = Transformations.switchMap(getVehicleResult) { it.loading }
    val getVehicleResponse = Transformations.switchMap(getVehicleResult) { it.listItem }
    fun getVehicle(id:Int) {
        viewModelScope.launch {
            getVehicleResult.postValue(vehicleRepository.getDetailVehicleItem(id))
        }
    }

}