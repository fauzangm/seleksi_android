package com.eduside.seleksiandroid.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo

@Dao
interface FilmDao {

    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFilm(item: FilmVo)
    //INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFilm(item: List<FilmVo>)

    //delete
    @Delete
    suspend fun deletFilm(item: FilmVo)

    //get
    @Query("SELECT * FROM list_film ORDER BY id ASC")
    fun getFIlm(): LiveData<List<FilmVo>>

}