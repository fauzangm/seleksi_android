package com.eduside.seleksiandroid.data.local.db.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "list_people")
data class PeopleVo(


    @PrimaryKey(autoGenerate = false)
    @NonNull
    var name: String = "",

    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "mass")
    var mass: String? = null,
    @ColumnInfo(name = "hair_color")
    var hair_color: String? = null,
    @ColumnInfo(name = "skin_color")
    var skin_color: String? = null,
    @ColumnInfo(name = "birth_year")
    var birth_year: String? = null,
    @ColumnInfo(name = "gender")
    var gender: String? = null,
    @ColumnInfo(name = "eye_color")
    var eye_color: String? = null,
) : Parcelable