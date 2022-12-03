package com.eduside.seleksiandroid.data.repository.startship

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.dao.ShipDao
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo
import com.eduside.seleksiandroid.data.local.db.entities.ShipVo
import com.eduside.seleksiandroid.data.local.db.entities.SpeciesVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.*
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import com.eduside.seleksiandroid.data.repository.vehicle.GetDetailVehicleResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStartshipRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val shipDao: ShipDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regItem = MutableLiveData<GetStartshipsResponse>()
    val shipVoItem = MutableLiveData<List<ShipVo>>()
    private var id = 0

    //search id
    fun getShip(searchQuery: String): Flow<List<ShipVo>> {
        return shipDao.getShip(searchQuery)
    }

    suspend fun getItemShip() {
        withContext(ioDispatcher) {
            val result = shipDao.getShip()
            result.let {
                shipVoItem.postValue(it)
            }
        }
    }

    suspend fun getStartship(): GetStartshipResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val check = shipDao.getShip()
                val getResponse = apiServices.getStartship()
                if (check.isEmpty()) {
                    if (getResponse.isSuccessful) {
                        getResponse.body()?.let {
                            it.results?.forEach { data ->
                                saveShip(it.results)
                            }
                            regItem.postValue(it)
                        }
                    } else {
                        error.postValue(getResponse.errorBody()?.string().toString())
                    }
                    loading.postValue(false)
                }
            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetStartshipResult(error, loading, regItem)
        }

    }


    private val regDetailItem = MutableLiveData<GetDetailShipResponse>()
    suspend fun getDetaiShipItem(id: Int): GetDetailStartshipResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val getResponse = apiServices.getDetailShip(id)
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
            return@withContext GetDetailStartshipResult(error, loading, regDetailItem)
        }

    }

    //    it.pilots?.forEach { vehicle ->
//        val lengt = vehicle.length - 2
//        var dataID = ""
//        if (vehicle[lengt - 1] != '/') {
//            val data = vehicle[lengt]
//            val data2 =vehicle[lengt -1]
//            dataID = data2.toString() + data.toString()
//        } else {
//            val data = vehicle[lengt]
//            dataID = data.toString()
//        }
//        viewModel.getPeople(dataID.toInt())
//    }
    private suspend fun saveShip(data: List<ResultsStartShipItem>) {
        if (data.isNotEmpty()) {
            val startShipItem: ArrayList<ShipVo> = arrayListOf()
            data.forEach { listItem ->
                val url = listItem.url
                val lengt = url?.length!! - 2
                var dataID = ""
                if (url[lengt - 1] != '/') {
                    val data = url[lengt]
                    val data2 = url[lengt - 1]
                    dataID = data2.toString() + data.toString()
                } else {
                    val data = url[lengt]
                    dataID = data.toString()
                }
                Log.e("DatIDSHIP",dataID)
                startShipItem.add(
                    ShipVo(
                        id = dataID.toInt(),
                        name = listItem.name!!
                    )
                )


                delay(200L)
                shipDao.addShip(startShipItem)
            }

        }

    }
}