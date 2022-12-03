package com.eduside.seleksiandroid.data.repository.film

import androidx.lifecycle.LiveData
import com.eduside.seleksiandroid.data.remote.response.GetDetailFilmResponse
import com.eduside.seleksiandroid.data.remote.response.GetFilmResponse
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse

data class GetDetailFilmResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val listItem: LiveData<GetDetailFilmResponse>,
)