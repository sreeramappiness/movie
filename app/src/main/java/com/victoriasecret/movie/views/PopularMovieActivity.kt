package com.victoriasecret.movie.views

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.victoriasecret.movie.BaseActivity
import com.victoriasecret.movie.R
import com.victoriasecret.movie.model.movieList.PopularResults
import com.victoriasecret.movie.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_popular_movie.*
import java.util.*

class PopularMovieActivity : BaseActivity(), PopularAdapter.OnItemClickListener {
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var mainViewModel: MainViewModel
    var popularListMain: MutableList<PopularResults> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movie)

        initialize()
        setViews()
        registerObservers()


    }

    fun filterList(characters: String) {
        var filteredPopularList: ArrayList<PopularResults> = ArrayList()
        val filterList = popularListMain.filter {
            it.name!!.toLowerCase(Locale.getDefault())
                .contains(characters.toLowerCase(Locale.getDefault())) ||
                    it.originalLanguage!!.toLowerCase(Locale.getDefault())
                        .contains(characters.toLowerCase(Locale.getDefault()))
        }
        filteredPopularList.addAll(filterList)
        popularAdapter.filterList(filteredPopularList)

    }

    private fun initialize() {
        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        popularAdapter = PopularAdapter(this, this)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //do nothing
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                filterList(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {
                //do nothing
            }
        })

    }

    private fun setViews() {
        rvItemPopular.layoutManager = GridLayoutManager(this, 2)
        rvItemPopular.adapter = popularAdapter
    }

    private fun registerObservers() {
        mainViewModel.getPopular()
        loader.visibility = View.VISIBLE
        mainViewModel.popularSuccessLiveData.observe(this, Observer { popularList ->
            popularList?.let {
                loader.visibility = View.GONE
                popularListMain.addAll(it.results)
                popularAdapter.setDetails(it.results)
            }
        })

        mainViewModel.popularFailureLiveData.observe(this, Observer { isFailed ->

            isFailed?.let {
                loader.visibility = View.GONE
                Toast.makeText(
                    this,
                    this.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    override fun onItemClick(movieId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("movieId", movieId.toString())
        startActivity(intent)
    }
}