package com.ridhoafni.codeinmoviedb.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ridhoafni.codeinmoviedb.databinding.ItemTrailerBinding
import com.ridhoafni.core.R
import com.ridhoafni.core.data.local.entity.TrailerEntity
import com.ridhoafni.core.utils.Constants
import timber.log.Timber
import java.util.*

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.ListViewHolder>() {

    private var trailerList = ArrayList<TrailerEntity>()

    fun setData(newListData: List<TrailerEntity>?) {
        if (newListData == null) return
        trailerList.clear()
        trailerList.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemTrailerBinding) : RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(trailer: TrailerEntity){
            Timber.e(trailer.name)
            val thumbnail =
                "https://img.youtube.com/vi/" + trailer.key.toString() + "/hqdefault.jpg"
            with(binding){
                Glide.with(itemView.context)
                    .load(thumbnail)
                    .placeholder(R.color.md_grey_200)
                    .into(imageTrailer)

                binding.trailerName.setText(trailer.name)

                binding.cardTrailer.setOnClickListener {
                    val appIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube:" + trailer.key)
                    )
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(Constants.YOUTUBE_WEB_URL + trailer.key)
                    )
                    if (appIntent.resolveActivity(itemView.context.getPackageManager()) != null) {
                        itemView.context.startActivity(appIntent)
                    } else {
                        itemView.context.startActivity(webIntent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemTrailerBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = trailerList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(trailerList[position])
    }

}