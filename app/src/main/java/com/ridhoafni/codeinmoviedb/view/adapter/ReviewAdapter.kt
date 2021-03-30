package com.ridhoafni.codeinmoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.ridhoafni.codeinmoviedb.databinding.ItemReviewBinding
import com.ridhoafni.core.data.local.entity.ReviewEntity
import java.util.*

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ListViewHolder>() {

    private var reviewList = ArrayList<ReviewEntity>()

    fun setData(newListData: List<ReviewEntity>?) {
        if (newListData == null) return
        reviewList.clear()
        reviewList.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(review: ReviewEntity){
            // review user image
            val generator: ColorGenerator = ColorGenerator.MATERIAL
            val color: Int = generator.getRandomColor()
            val drawable = TextDrawable.builder()
                .buildRound(review.author?.substring(0, 1)?.toUpperCase(Locale.ROOT), color)
            with(binding){
                imageAuthor.setImageDrawable(drawable)
                textAuthor.text = review.author
                textContent.text = review.content
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemReviewBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = reviewList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

}