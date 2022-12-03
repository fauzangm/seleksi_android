package com.eduside.seleksiandroid.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.seleksiandroid.data.local.db.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VehiclesDao {

    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVehicles(item: VehiclesVo)
    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVehicles(item: List<VehiclesVo>)

    //delete
    @Delete
    suspend fun deletVehicles(item: VehiclesVo)

    //get
    @Query("SELECT * FROM list_vehicles ORDER BY id ASC")
    suspend fun getVehicles(): List<VehiclesVo>


    //getFromID
    @Query("SELECT * FROM list_vehicles WHERE id LIKE :searchQuery")
    fun getVehicle(searchQuery: String): Flow<List<VehiclesVo>>

}