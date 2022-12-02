package com.eduside.seleksiandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetPlanetsResponse(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsPlanetItem>? = null
)

data class ResultsPlanetItem(

	@field:SerializedName("films")
	val films: List<String?>? = null,

	@field:SerializedName("edited")
	val edited: String? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("climate")
	val climate: String? = null,

	@field:SerializedName("rotation_period")
	val rotationPeriod: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("population")
	val population: String? = null,

	@field:SerializedName("orbital_period")
	val orbitalPeriod: String? = null,

	@field:SerializedName("surface_water")
	val surfaceWater: String? = null,

	@field:SerializedName("diameter")
	val diameter: String? = null,

	@field:SerializedName("gravity")
	val gravity: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("residents")
	val residents: List<String?>? = null,

	@field:SerializedName("terrain")
	val terrain: String? = null
)
