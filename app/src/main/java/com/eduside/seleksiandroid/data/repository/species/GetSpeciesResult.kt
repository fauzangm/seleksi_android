package com.eduside.seleksiandroid.data.repository.species

import androidx.lifecycle.LiveData
import com.eduside.seleksiandroid.data.remote.response.GetPlanetsResponse
import com.eduside.seleksiandroid.data.remote.response.GetSpeciesResponse
import com.eduside.seleksiandroid.data.remote.response.GetVehiclesResponse
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse

data class GetSpeciesResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val listItem: LiveData<GetSpeciesResponse>,
)