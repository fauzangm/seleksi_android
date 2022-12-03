package com.eduside.seleksiandroid.data.repository.planet

import androidx.lifecycle.LiveData
import com.eduside.seleksiandroid.data.remote.response.GetDetailPlanetResponse
import com.eduside.seleksiandroid.data.remote.response.GetPlanetsResponse
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse

data class GetDetailPlanetResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val listItem: LiveData<GetDetailPlanetResponse>,
)