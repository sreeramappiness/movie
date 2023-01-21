package com.victoriasecret.movie.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.victoriasecret.movie.R
import com.victoriasecret.movie.model.movieList.PopularResults
import java.util.*
import kotlin.collections.ArrayList

class PopularAdapter(private val context: Context, private val onMovieClickListener: OnItemClickListener) : RecyclerView.Adapter<PopularAdapter.UserViewHolder>() {

    private var detailsList: MutableList<PopularResults> = ArrayList()

    fun setDetails(popularList: MutableList<PopularResults>) {

        detailsList.addAll(popularList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false)

        return UserViewHolder(view)
    }

    fun filterList(filteredList: ArrayList<PopularResults>) {

        detailsList = filteredList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return detailsList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val popularList = detailsList[position]

        holder.movieName.text = popularList.name.toString()
        holder.ratingBar.rating = popularList.voteAverage?.toFloat() ?: 1F

        var thumbNail = context.getString(R.string.image_url)+popularList.backdropPath

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
        Glide.with(context)
            .load(thumbNail)
            .apply(requestOptions)
            .skipMemoryCache(true)
            .into(holder.imageView)

        holder.itemPopular.setOnClickListener {
            popularList.id?.let { it1 -> onMovieClickListener.onItemClick(it1) }
        }

    }

    class UserViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

        val movieName = parent.findViewById<TextView>(R.id.tvItemMovieName)!!
        var imageView: ImageView = itemView.findViewById(R.id.ivPopularImage)
        var ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        var itemPopular: RelativeLayout = itemView.findViewById(R.id.itemPopular)

    }

    interface OnItemClickListener {
        fun onItemClick(movieId: Int)
    }
}