package com.eduside.seleksiandroid.data.repository.vehicle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.dao.VehiclesDao
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo
import com.eduside.seleksiandroid.data.local.db.entities.ShipVo
import com.eduside.seleksiandroid.data.local.db.entities.VehiclesVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.*
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import com.eduside.seleksiandroid.data.repository.film.GetDetailFilmResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetVehicleRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val vehiclesDao: VehiclesDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regItem = MutableLiveData<GetVehiclesResponse>()
    val vehicleItemVo = MutableLiveData<List<VehiclesVo>>()
    private var id = 0

    //search id
    fun getVehicle(searchQuery: String): Flow<List<VehiclesVo>> {
        return vehiclesDao.getVehicle(searchQuery)
    }

    suspend fun getItemVehicle() {
        withContext(ioDispatcher) {
            val result = vehiclesDao.getVehicles()
            result.let {
                vehicleItemVo.postValue(it)
            }
        }
    }

    suspend fun getVehicle(): GetVehicleResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val check = vehiclesDao.getVehicles()
                val getResponse = apiServices.getVehicles()
                if (check.isEmpty()) {


                    if (getResponse.isSuccessful) {
                        getResponse.body()?.let {
                            it.results?.forEach { data ->
                                saveVehicles(it.results)
                            }
                            regItem.postValue(it)
                        }
                    } else {
                        error.postValue(getResponse.errorBody()?.string().toString())
                    }
                }
                loading.postValue(false)

            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetVehicleResult(error, loading, regItem)
        }

    }

    private val regDetailItem = MutableLiveData<GetDetailVehiclesResponse>()
    suspend fun getDetailVehicleItem(id:Int): GetDetailVehicleResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val getResponse = apiServices.getDetailVehicle(id)
                if (getResponse.isSuccessful) {
                    getResponse.body()?.let {
                        regDetailItem.postValue(it)
                    }
                } else {
                    error.postValue(getResponse.errorBody()?.string().toString())
                }


                loading.postValue(false)

            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetDetailVehicleResult(error, loading, regDetailItem)
        }

    }

    private suspend fun saveVehicles(data: List<ResultsVehiclesItem>) {
        if (data.isNotEmpty()) {
            val vehiclesItem: ArrayList<VehiclesVo> = arrayListOf()
            data.forEach { listItem ->
                id++
                vehiclesItem.add(
                    VehiclesVo(
                        id = id - 90,
                        name = listItem.name!!
                    )
                )

            }
            delay(200L)
            vehiclesDao.addVehicles(vehiclesItem)
        }

    }

}