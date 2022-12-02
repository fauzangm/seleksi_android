package com.eduside.seleksiandroid.data.repository.startship

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.dao.ShipDao
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.ShipVo
import com.eduside.seleksiandroid.data.local.db.entities.SpeciesVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.*
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
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
    private var id = 0

    suspend fun getStartship() : GetStartshipResult {
        return withContext(ioDispatcher){
            loading.postValue(true)
            try {
                val getResponse = apiServices.getStartship()
                if (getResponse.isSuccessful){
                    getResponse.body()?.let {
                        it.results?.forEach {data->
                            saveShip(it.results)
                        }
                        regItem.postValue(it)
                    }
                } else{
                    error.postValue(getResponse.errorBody()?.string().toString())
                }
                loading.postValue(false)

            }catch (e: Exception){
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetStartshipResult(error, loading, regItem)
        }

    }

    private suspend fun saveShip(data: List<ResultsStartShipItem>) {
        if (data.isNotEmpty()){
            val startShipItem: ArrayList<ShipVo> = arrayListOf()
            data.forEach { listItem ->
                id++
                startShipItem.add(
                    ShipVo(
                        id = id-90,
                        name = listItem.name!!
                    )
                )

            }
            delay(200L)
            shipDao.addShip(startShipItem)
        }

    }

}