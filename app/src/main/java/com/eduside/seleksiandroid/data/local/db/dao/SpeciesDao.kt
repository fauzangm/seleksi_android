package com.eduside.seleksiandroid.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.seleksiandroid.data.local.db.entities.*
import kotlinx.coroutines.flow.Flow

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
    suspend fun getSpecies(): List<SpeciesVo>

    //getFromID
    @Query("SELECT * FROM list_species WHERE id LIKE :searchQuery")
    fun getSpecies(searchQuery: String): Flow<List<SpeciesVo>>

}