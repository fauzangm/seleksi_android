package com.eduside.seleksiandroid.data.repository.people

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.observe
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.GetDetailPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPeopleRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val peopleDao: PeopleDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regItem = MutableLiveData<GetPeopleResponse>()
    val itemPeople = MutableLiveData<List<PeopleVo>>()
    private var id = 0

    suspend fun getPeople() {
        withContext(ioDispatcher) {
            val result = peopleDao.getPeople()
            result.let {
                itemPeople.postValue(it)
            }
        }
    }

    //search id
    fun getPeople(searchQuery: String): Flow<List<PeopleVo>> {
        return peopleDao.getPeople(searchQuery)
    }
    suspend fun getPeopleItem(): GetPeopleResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val check = peopleDao.getPeople()
                if (check.isEmpty()) {
                    val getResponse = apiServices.getPeople()
                    if (getResponse.isSuccessful) {
                        getResponse.body()?.let {
                            it.results?.forEach { data ->
                                saveGetItem(it.results)
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
            return@withContext GetPeopleResult(error, loading, regItem)
        }

    }


    private val regDetailItem = MutableLiveData<GetDetailPeopleResponse>()
    suspend fun getPeopleDetailItem(id: Int): GetDetailPeopleResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val getResponse = apiServices.getDetailPeople(id)
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
            return@withContext GetDetailPeopleResult(error, loading, regDetailItem)
        }

    }

    private suspend fun saveGetItem(data: List<ResultsPeopleItem>) {
        if (data.isNotEmpty()) {
            val peopleItem: ArrayList<PeopleVo> = arrayListOf()
            data.forEach { listItem ->
                id++
                peopleItem.add(
                    PeopleVo(
                        id = id - 90,
                        name = listItem.name!!,
                        mass = listItem.mass,
                        hair_color = listItem.hairColor,
                        eye_color = listItem.eyeColor,
                        skin_color = listItem.skinColor,
                        birth_year = listItem.birthYear,
                        gender = listItem.gender,
                    )
                )

            }
            delay(200L)
            peopleDao.addPeople(peopleItem)
        }

    }
}