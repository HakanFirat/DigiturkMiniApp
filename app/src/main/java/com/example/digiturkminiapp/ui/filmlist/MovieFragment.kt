package com.example.digiturkminiapp.ui.filmlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.digiturkminiapp.R
import com.example.digiturkminiapp.core.BaseFragment
import com.example.digiturkminiapp.interfaces.RecyclerItemClickListener
import com.example.digiturkminiapp.model.Category
import com.example.digiturkminiapp.model.Film
import com.example.digiturkminiapp.model.FilmListResponse
import com.example.digiturkminiapp.network.State
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment(private val category: Category) : BaseFragment() {

    private lateinit var viewModel: FilmListViewModel
    private var filmList: List<Film> = emptyList()
    private var adapter: MovieRecyclerAdapter? = null

    override val layoutResource: Int
        get() = R.layout.fragment_movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FilmListViewModel::class.java)
        observeViewModel()
    }

    override fun initViewsOnViewCreated(view: View) {
        super.initViewsOnViewCreated(view)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        category.id?.let {
            viewModel.getFilmList(it.toInt())
        }
    }

    private fun observeViewModel() {
        val observer = Observer<State> { value ->
            value?.let {
                when (it) {
                    is State.Loading -> {
                        Log.e("Loading","Loading")
                    }

                    is State.Success<*> -> {
                        Log.e("Success","Success")
                        renderModel(it.data!!)
                    }

                    is State.Error -> {
                        Log.e("Error","Error")
                    }
                }
            }
        }
        viewModel.state.observe(this, observer)
    }

    private fun renderModel(model: Any) {
        if (model is FilmListResponse) {
            filmList = emptyList()
            filmList = model.results

            if (filmList.isNotEmpty()){
                adapter = MovieRecyclerAdapter(filmList)
                adapter?.setItemClickListener(object: RecyclerItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                    }
                })
                recyclerView.adapter = adapter
            }
        }
    }

}
