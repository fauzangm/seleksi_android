package com.eduside.seleksiandroid.data.repository.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
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
    private var id =0

    fun getPeople(): LiveData<List<PeopleVo>> = peopleDao.getPeople()

    suspend fun getPeopleItem() : GetPeopleResult {
        return withContext(ioDispatcher){
            loading.postValue(true)
            try {
                val getResponse = apiServices.getPeople()
                if (getResponse.isSuccessful){
                    getResponse.body()?.let {
                        it.results?.forEach { data->
                            saveGetItem(it.results)
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
            return@withContext GetPeopleResult(error, loading, regItem)
        }

    }

    private suspend fun saveGetItem(data: List<ResultsPeopleItem>) {
        if (data.isNotEmpty()){
            val peopleItem: ArrayList<PeopleVo> = arrayListOf()
            data.forEach { listItem ->
                id++
                peopleItem.add(
                    PeopleVo(
                        id = id-90,
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