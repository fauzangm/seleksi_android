package com.eduside.seleksiandroid.ui.listItem

import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo
import com.eduside.seleksiandroid.data.local.db.entities.SpeciesVo


class ItemDataSpeciesEvent(var data: SpeciesVo) {
    fun itemClicked(data: SpeciesVo) {
        this.data = data
    }
}