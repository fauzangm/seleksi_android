package com.eduside.seleksiandroid.ui.listItem

import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo


class ItemDataPlanetEvent(var data: PlanetVo) {
    fun itemClicked(data: PlanetVo) {
        this.data = data
    }
}