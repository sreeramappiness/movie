package com.victoriasecret.movie.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.victoriasecret.movie.BaseActivity
import com.victoriasecret.movie.R
import com.victoriasecret.movie.model.details.DetailsList
import com.victoriasecret.movie.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.loader
import kotlinx.android.synthetic.main.activity_popular_movie.*

class DetailsActivity : BaseActivity() {
    private lateinit var mainViewModel: MainViewModel
    lateinit var movieId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        movieId = intent.getStringExtra("movieId")!!
        initialize()
        registerObservers()
    }

    private fun initialize(){
        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
    }

    private fun registerObservers() {
        loader.visibility = View.VISIBLE
        mainViewModel.getDetails(movieId)
        mainViewModel.detailsListSuccessLiveData.observe(this, Observer { detailsList ->

            detailsList?.let {
                loader.visibility = View.GONE
                setData(it)
            }
        })

        mainViewModel.detailsListFailureLiveData.observe(this, Observer { isFailed ->
            isFailed?.let {
                loader.visibility = View.GONE
                Toast.makeText(this, this.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setData(details: DetailsList) {
        var thumbNail = this.getString(R.string.image_url) +details.backdropPath
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))

        tvMovieName.text = details.name
        Glide.with(this)
            .load(thumbNail)
            .apply(requestOptions)
            .skipMemoryCache(true)
            .into(ivMovieImage)

        ratingBarDetails.rating = details.voteAverage?.toFloat() ?: 1F
        tvOverview.text = details.overview
    }

}