package com.eduside.seleksiandroid.data.repository.vehicle

import androidx.lifecycle.LiveData
import com.eduside.seleksiandroid.data.remote.response.GetPlanetsResponse
import com.eduside.seleksiandroid.data.remote.response.GetVehiclesResponse
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse

data class GetVehicleResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val listItem: LiveData<GetVehiclesResponse>,
)