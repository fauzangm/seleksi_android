package com.eduside.seleksiandroid.di

import android.content.Context
import androidx.room.Room
import com.eduside.seleksiandroid.data.local.db.AppDB
import com.eduside.seleksiandroid.data.local.db.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModules {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDB {
        return Room
            .databaseBuilder(context, AppDB::class.java, "seleksiAndroid.db")
            .fallbackToDestructiveMigration()
            .build()
    }
////
    @Singleton
    @Provides
    fun provideDataPeople(db: AppDB): PeopleDao {
        return db.peopleDao()
    }

    @Singleton
    @Provides
    fun provideDataFilm(db: AppDB): FilmDao {
        return db.filmDao()
    }

    @Singleton
    @Provides
    fun provideDataSpecies(db: AppDB): SpeciesDao {
        return db.speciesDao()
    }

    @Singleton
    @Provides
    fun provideDataShip(db: AppDB): ShipDao {
        return db.shipDao()
    }

    @Singleton
    @Provides
    fun provideDataPlanet(db: AppDB): PlanetDao {
        return db.planetDao()
    }

    @Singleton
    @Provides
    fun provideDataVehicles(db: AppDB): VehiclesDao {
        return db.vehiclesDao()
    }


}