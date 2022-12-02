package com.eduside.seleksiandroid.ui.listItem

import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo


class ItemDataPeopleEvent(var data: PeopleVo) {
    fun itemClicked(data: PeopleVo) {
        this.data = data
    }
}