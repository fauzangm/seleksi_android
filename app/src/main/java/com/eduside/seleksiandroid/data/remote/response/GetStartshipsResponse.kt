package com.eduside.seleksiandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetStartshipsResponse(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsStartShipItem>? = null
)

data class ResultsStartShipItem(

	@field:SerializedName("max_atmosphering_speed")
	val maxAtmospheringSpeed: String? = null,

	@field:SerializedName("cargo_capacity")
	val cargoCapacity: String? = null,

	@field:SerializedName("films")
	val films: List<String?>? = null,

	@field:SerializedName("passengers")
	val passengers: String? = null,

	@field:SerializedName("pilots")
	val pilots: List<Any?>? = null,

	@field:SerializedName("edited")
	val edited: String? = null,

	@field:SerializedName("consumables")
	val consumables: String? = null,

	@field:SerializedName("MGLT")
	val mGLT: String? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("length")
	val length: String? = null,

	@field:SerializedName("starship_class")
	val starshipClass: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("manufacturer")
	val manufacturer: String? = null,

	@field:SerializedName("crew")
	val crew: String? = null,

	@field:SerializedName("hyperdrive_rating")
	val hyperdriveRating: String? = null,

	@field:SerializedName("cost_in_credits")
	val costInCredits: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("model")
	val model: String? = null
)
