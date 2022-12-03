package com.eduside.seleksiandroid.ui.listItem

import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.ShipVo


class ItemDataShipEvent(var data: ShipVo) {
    fun itemClicked(data: ShipVo) {
        this.data = data
    }
}