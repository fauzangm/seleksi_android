package com.eduside.seleksiandroid.ui

import androidx.lifecycle.*
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.repository.people.GetPeopleRepository
import com.eduside.seleksiandroid.data.repository.people.GetPeopleResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemViewModel @Inject constructor(
    private val peopleRepository: GetPeopleRepository
) : ViewModel() {

    fun getPeople(): LiveData<List<PeopleVo>> = peopleRepository.getPeople()


}