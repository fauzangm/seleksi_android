package com.eduside.seleksiandroid.data.repository.planet

import android.media.Image.Plane
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.dao.PlanetDao
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.GetPlanetsResponse
import com.eduside.seleksiandroid.data.remote.response.ResultsFilmItem
import com.eduside.seleksiandroid.data.remote.response.ResultsPlanetItem
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPlanetRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val planetDao: PlanetDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regItem = MutableLiveData<GetPlanetsResponse>()
    val planetVoItem = MutableLiveData<List<PlanetVo>>()
    private var id = 0

    suspend fun getItemPlanet() {
        withContext(ioDispatcher) {
            val result = planetDao.getPlanet()
            result.let {
                planetVoItem.postValue(it)
            }
        }
    }

    suspend fun getPlanet(): GetPlanetResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val check = planetDao.getPlanet()
                if (check.isEmpty()) {
                    val getResponse = apiServices.getPlanet()
                    if (getResponse.isSuccessful) {
                        getResponse.body()?.let {
                            it.results?.forEach { data ->
                                savePLanet(it.results)
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
            return@withContext GetPlanetResult(error, loading, regItem)
        }

    }

    private suspend fun savePLanet(data: List<ResultsPlanetItem>) {
        if (data.isNotEmpty()) {
            val planetItem: ArrayList<PlanetVo> = arrayListOf()
            data.forEach { listItem ->
                id++
                planetItem.add(
                    PlanetVo(
                        id = id - 90,
                        name = listItem.name!!
                    )
                )

            }
            delay(200L)
            planetDao.addPlanet(planetItem)
        }

    }

}