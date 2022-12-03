package com.eduside.seleksiandroid.ui.listItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduside.seleksiandroid.data.local.db.entities.PlanetVo
import com.eduside.seleksiandroid.databinding.ItemListPlanetItemBinding
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class ListPlanetAdapter @Inject constructor() :
    ListAdapter<PlanetVo, ListPlanetAdapter.ViewHolder>(ListDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListPlanetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvNama.text = data.name
        holder.binding.cvContainer.setOnClickListener {
            EventBus.getDefault().post(ItemDataPlanetEvent(data))
        }
    }

    class ViewHolder(itemBinding: ItemListPlanetItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var binding: ItemListPlanetItemBinding = itemBinding
    }

    class ListDiffUtill : DiffUtil.ItemCallback<PlanetVo>() {
        override fun areItemsTheSame(oldItem: PlanetVo, newItem: PlanetVo): Boolean {
            return newItem.id== oldItem.id
        }

        override fun areContentsTheSame(oldItem: PlanetVo, newItem: PlanetVo): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}