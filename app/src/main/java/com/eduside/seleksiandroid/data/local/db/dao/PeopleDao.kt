package com.eduside.seleksiandroid.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPeople(item: PeopleVo)
    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPeople(item: List<PeopleVo>)

    //delete
    @Delete
    suspend fun deletPeople(item: PeopleVo)

    //get
    @Query("SELECT * FROM list_people ORDER BY id ASC")
    suspend fun getPeople(): List<PeopleVo>

    //getFromID
    @Query("SELECT * FROM list_people WHERE id LIKE :searchQuery")
    fun getPeople(searchQuery: String): Flow<List<PeopleVo>>

}