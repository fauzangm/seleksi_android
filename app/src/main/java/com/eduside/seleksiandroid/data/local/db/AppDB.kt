package com.eduside.seleksiandroid.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eduside.seleksiandroid.data.local.db.dao.*
import com.eduside.seleksiandroid.data.local.db.entities.*

@Database(
    entities =
    [
        PeopleVo::class,
        FilmVo::class,
        PlanetVo::class,
        SpeciesVo::class,
        ShipVo::class,
        VehiclesVo::class
    ],
    version = 3,
    exportSchema = false
)

abstract class AppDB : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao
    abstract fun filmDao(): FilmDao
    abstract fun planetDao(): PlanetDao
    abstract fun shipDao(): ShipDao
    abstract fun vehiclesDao(): VehiclesDao
    abstract fun speciesDao(): SpeciesDao

}