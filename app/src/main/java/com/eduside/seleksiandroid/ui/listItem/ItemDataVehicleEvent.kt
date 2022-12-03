package com.eduside.seleksiandroid.ui.listItem

import com.eduside.seleksiandroid.data.local.db.entities.FilmVo
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.data.local.db.entities.ShipVo
import com.eduside.seleksiandroid.data.local.db.entities.VehiclesVo


class ItemDataVehicleEvent(var data: VehiclesVo) {
    fun itemClicked(data: VehiclesVo) {
        this.data = data
    }
}