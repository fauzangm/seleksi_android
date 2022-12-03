package com.eduside.seleksiandroid.data.remote

import com.eduside.seleksiandroid.data.remote.response.*
import com.eduside.seleksiandroid.data.remote.response.people.GetPeopleResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {


    @GET("people")
    suspend fun getPeople(
    ):Response<GetPeopleResponse>

    @GET("films")
    suspend fun getFilms(
    ):Response<GetFilmResponse>

    @GET("planets")
    suspend fun getPlanet(
    ):Response<GetPlanetsResponse>

    @GET("species")
    suspend fun getSpecies(
    ):Response<GetSpeciesResponse>

    @GET("vehicles")
    suspend fun getVehicles(
    ):Response<GetVehiclesResponse>

    @GET("starships")
    suspend fun getStartship(
    ):Response<GetStartshipsResponse>

    @GET("people/{id}")
    suspend fun getDetailPeople(
        @Path("id")id:Int
    ):Response<GetDetailPeopleResponse>

    @GET("films/{id}")
    suspend fun getDetailFilm(
        @Path("id")id:Int
    ):Response<GetDetailFilmResponse>

    @GET("vehicles/{id}")
    suspend fun getDetailVehicle(
        @Path("id")id:Int
    ):Response<GetDetailVehiclesResponse>

    @GET("species/{id}")
    suspend fun getDetailSpecies(
        @Path("id")id:Int
    ):Response<GetDetailHumanResponse>

    @GET("starships/{id}")
    suspend fun getDetailShip(
        @Path("id")id:Int
    ):Response<GetDetailShipResponse>

    @GET("planets/{id}")
    suspend fun getDetailPlanet(
        @Path("id")id:Int
    ):Response<GetDetailPlanetResponse>

}