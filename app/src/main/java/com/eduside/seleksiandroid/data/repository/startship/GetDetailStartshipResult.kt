package com.eduside.seleksiandroid.data.repository.startship

import androidx.lifecycle.LiveData
import com.eduside.seleksiandroid.data.remote.response.GetDetailShipResponse
import com.eduside.seleksiandroid.data.remote.response.GetPlanetsResponse
import com.eduside.seleksiandroid.data.remote.response.GetStartshipsResponse
import com.eduside.seleksiandroid.data.remote.response.GetVehiclesResponse
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse

data class GetDetailStartshipResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val listItem: LiveData<GetDetailShipResponse>,
)