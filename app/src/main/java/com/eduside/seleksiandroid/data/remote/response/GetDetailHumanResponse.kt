package com.eduside.seleksiandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetDetailHumanResponse(

	@field:SerializedName("films")
	val films: List<String?>? = null,

	@field:SerializedName("skin_colors")
	val skinColors: String? = null,

	@field:SerializedName("homeworld")
	val homeworld: String? = null,

	@field:SerializedName("edited")
	val edited: String? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("eye_colors")
	val eyeColors: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("classification")
	val classification: String? = null,

	@field:SerializedName("people")
	val people: List<String?>? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hair_colors")
	val hairColors: String? = null,

	@field:SerializedName("average_height")
	val averageHeight: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("designation")
	val designation: String? = null,

	@field:SerializedName("average_lifespan")
	val averageLifespan: String? = null
)
