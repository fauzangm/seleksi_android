package com.eduside.seleksiandroid.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo
import com.eduside.seleksiandroid.data.local.db.entities.SpeciesVo

@Dao
interface SpeciesDao {

    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSpecies(item: SpeciesVo)
    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSpecies(item: List<SpeciesVo>)

    //delete
    @Delete
    suspend fun deletSpecies(item: SpeciesVo)

    //get
    @Query("SELECT * FROM list_species ORDER BY id ASC")
    fun getSpecies(): LiveData<List<SpeciesVo>>

}