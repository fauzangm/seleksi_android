package com.eduside.seleksiandroid.data.local.db.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "list_ship")
data class ShipVo(


    @PrimaryKey(autoGenerate = false)
    @NonNull
    var name: String = "",

    @ColumnInfo(name = "id")
    var id: Int? = null
) : Parcelable