package com.eduside.seleksiandroid.data.repository.species

import android.widget.GridLayout.Spec
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.dao.SpeciesDao
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo
import com.eduside.seleksiandroid.data.local.db.entities.SpeciesVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.*
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSpeciesRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val speciesDao: SpeciesDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regItem = MutableLiveData<GetSpeciesResponse>()
    val speciesVoItem = MutableLiveData<List<SpeciesVo>>()
    private var id = 0


    suspend fun getItemSpecies() {
        withContext(ioDispatcher) {
            val result = speciesDao.getSpecies()
            result.let {
                speciesVoItem.postValue(it)
            }
        }
    }

    suspend fun geteSPecies(): GetSpeciesResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val check = speciesDao.getSpecies()
                val getResponse = apiServices.getSpecies()
                if (check.isEmpty()) {
                    if (getResponse.isSuccessful) {
                        getResponse.body()?.let {
                            it.results?.forEach { data ->
                                saveSpecies(it.results)
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
            return@withContext GetSpeciesResult(error, loading, regItem)
        }

    }

    private suspend fun saveSpecies(data: List<ResultsSpeciesItem>) {
        if (data.isNotEmpty()) {
            val speciesItem: ArrayList<SpeciesVo> = arrayListOf()
            data.forEach { listItem ->
                id++
                speciesItem.add(
                    SpeciesVo(
                        id = id - 90,
                        name = listItem.name!!
                    )
                )

            }
            delay(200L)
            speciesDao.addSpecies(speciesItem)
        }

    }
}