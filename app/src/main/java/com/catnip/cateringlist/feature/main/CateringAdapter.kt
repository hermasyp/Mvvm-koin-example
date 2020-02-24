package com.catnip.cateringlist.feature.main;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.catnip.cateringlist.R
import com.catnip.cateringlist.model.Catering
import kotlinx.android.synthetic.main.item_catering.view.*

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CateringAdapter(val itemClick: (Catering) -> Unit) :
    RecyclerView.Adapter<CateringAdapter.CateringViewHolder>() {


    private var items: MutableList<Catering> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateringViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_catering, parent, false)
        return CateringViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: CateringViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(items: List<Catering>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addData(items: List<Catering>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class CateringViewHolder(view: View, val itemClick: (Catering) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bindView(item: Catering) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                itemView.img_catering?.load(this.catererLogo)
                itemView.tv_catering_name?.text = this.catererName
                itemView.rating_view_catering?.rating = this.rating.toFloat()
                itemView.tv_catering_tier?.text = this.tierName
                itemView.img_tier_icon?.load(this.tierIcon)
                itemView.tv_catering_distance?.text = this.distance
            }

        }
    }

}