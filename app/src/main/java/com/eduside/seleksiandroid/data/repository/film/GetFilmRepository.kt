package com.eduside.seleksiandroid.data.repository.film

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.seleksiandroid.data.local.db.dao.FilmDao
import com.eduside.seleksiandroid.data.local.db.dao.PeopleDao
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.remote.ApiServices
import com.eduside.seleksiandroid.data.remote.response.GetFilmResponse
import com.eduside.seleksiandroid.data.remote.response.ResultsFilmItem
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.ResultsPeopleItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFilmRepository @Inject constructor(
    private val apiServices: ApiServices,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val filmDao: FilmDao
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regItem = MutableLiveData<GetFilmResponse>()
    val filmVoItem = MutableLiveData<List<FilmVo>>()
    private var id = 0


    suspend fun getFilm() {
        withContext(ioDispatcher) {
            val result = filmDao.getFIlm()
            result.let {
                filmVoItem.postValue(it)
            }
        }
    }

    suspend fun clearFilm() {
        withContext(ioDispatcher) {
            filmDao.deletAll()
        }
    }

    suspend fun getFilmItem(): GetFilmResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val check = filmDao.getFIlm()
                if (check.isEmpty()) {
                    val getResponse = apiServices.getFilms()
                    if (getResponse.isSuccessful) {
                        getResponse.body()?.let {
                            it.results?.forEach { data ->
                                saveFilm(it.results)
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
            return@withContext GetFilmResult(error, loading, regItem)
        }

    }

    private suspend fun saveFilm(data: List<ResultsFilmItem>) {
        if (data.isNotEmpty()) {
            val filmItem: ArrayList<FilmVo> = arrayListOf()
            data.forEach { listItem ->
                id++
                filmItem.add(
                    FilmVo(
                        id = id - 30,
                        name = listItem.title!!
                    )
                )

            }
            delay(200L)
            filmDao.addFilm(filmItem)
        }

    }

}