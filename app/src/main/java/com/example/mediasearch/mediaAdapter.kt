package com.example.mediasearch

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class mediaAdapter(private val listener: ImageItemClicked) : RecyclerView.Adapter<viewHolder>() {

    private val mItems : ArrayList<ItemsViewModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_media , parent , false)
        val viewHolder = viewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(mItems[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val currentItem = mItems[position]
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.mediaView)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun updateImage(updatedImages : ArrayList<ItemsViewModel>){
        mItems.clear()
        mItems.addAll(updatedImages)

        notifyDataSetChanged()
    }
}

class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mediaView :ImageView = itemView.findViewById(R.id.media)

}

interface ImageItemClicked{
    fun onItemClicked(item : ItemsViewModel)
}
