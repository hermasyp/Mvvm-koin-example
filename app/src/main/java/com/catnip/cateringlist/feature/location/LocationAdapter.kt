package com.catnip.cateringlist.feature.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.cateringlist.R
import com.catnip.cateringlist.model.Location
import kotlinx.android.synthetic.main.item_location.view.*

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LocationAdapter(val itemClick: (Location) -> Unit) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private var items: MutableList<Location> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(items: List<Location>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addData(items: List<Location>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class LocationViewHolder(view: View, val itemClick: (Location) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bindView(item: Location) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                itemView.tv_title_location?.text = this.name
            }

        }
    }

}