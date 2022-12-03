package com.eduside.seleksiandroid.data.repository.people

import androidx.lifecycle.LiveData
import com.eduside.seleksiandroid.data.remote.response.GetDetailPeopleResponse
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse

data class GetDetailPeopleResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val listItem: LiveData<GetDetailPeopleResponse>,
)