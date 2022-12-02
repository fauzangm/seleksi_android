package com.eduside.seleksiandroid.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo

@Dao
interface PlanetDao {

    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlanet(item: PlanetVo)
    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlanet(item: List<PlanetVo>)

    //delete
    @Delete
    suspend fun deletPlanet(item: PlanetVo)

    //get
    @Query("SELECT * FROM list_planet ORDER BY id ASC")
    fun getPlanet(): LiveData<List<PlanetVo>>

}