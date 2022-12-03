package com.eduside.seleksiandroid.ui.listItem

import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo


class ItemDataFilmEvent(var data: FilmVo) {
    fun itemClicked(data: FilmVo) {
        this.data = data
    }
}