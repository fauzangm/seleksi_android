package com.eduside.seleksiandroid.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.ShipVo

@Dao
interface ShipDao {

    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShip(item: ShipVo)
    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShip(item: List<ShipVo>)

    //delete
    @Delete
    suspend fun deletShip(item: ShipVo)

    //get
    @Query("SELECT * FROM list_ship ORDER BY id ASC")
    suspend fun getShip(): List<ShipVo>

}