package com.eduside.seleksiandroid.ui.listItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduside.seleksiandroid.data.local.db.entities.PeopleVo
import com.eduside.seleksiandroid.databinding.ItemListPeopleItemBinding
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class ListPeopleAdapter @Inject constructor() :
    ListAdapter<PeopleVo, ListPeopleAdapter.ViewHolder>(ListDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListPeopleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvNama.text = data.name
//        Glide
//            .with(holder.itemView.context)
//            .load(data.image)
//            .centerCrop()
//            .placeholder(R.drawable.ic_defaultimage)
//            .into(holder.binding.imgKambing)
        holder.binding.cvContainer.setOnClickListener {
            EventBus.getDefault().post(ItemDataPeopleEvent(data))
        }
    }

    class ViewHolder(itemBinding: ItemListPeopleItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var binding: ItemListPeopleItemBinding = itemBinding
    }

    class ListDiffUtill : DiffUtil.ItemCallback<PeopleVo>() {
        override fun areItemsTheSame(oldItem: PeopleVo, newItem: PeopleVo): Boolean {
            return newItem.id== oldItem.id
        }

        override fun areContentsTheSame(oldItem: PeopleVo, newItem: PeopleVo): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}