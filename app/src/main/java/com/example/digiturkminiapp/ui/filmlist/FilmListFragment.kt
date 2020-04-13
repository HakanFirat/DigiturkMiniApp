package com.example.digiturkminiapp.ui.filmlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.digiturkminiapp.R
import com.example.digiturkminiapp.core.BaseFragment
import com.example.digiturkminiapp.model.Category
import com.example.digiturkminiapp.model.CategoryResponse
import com.example.digiturkminiapp.model.Film
import com.example.digiturkminiapp.model.FilmListResponse
import com.example.digiturkminiapp.network.State
import com.example.digiturkminiapp.utils.DialogUtis
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_film_list.*

/**
 * A simple [Fragment] subclass.
 */
class FilmListFragment : BaseFragment() {

    private lateinit var viewModel: FilmListViewModel
    private var categoryList: List<Category> = emptyList()
    private var filmList: List<Film> = emptyList()
    private lateinit var viewPagerAdapter: FilmListViewPagerAdapter

    override val layoutResource: Int
        get() = R.layout.fragment_film_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FilmListViewModel::class.java)
        observeViewModel()
    }

    override fun initViewsOnViewCreated(view: View) {
        super.initViewsOnViewCreated(view)

        hideBackButton()
        toolbarTitle = resources.getString(R.string.film_list_text)
        createViewPager()
        tabLayoutSelectedListener()

        // TODO: Request parametrelerini dynamic eklenebilir şekilde ayarladım.
        //  ViewModel classında bilerek static olarak eklemedim.
        viewModel.getCategoryList(apiKey = "3bb3e67969473d0cb4a48a0dd61af747",
                                  language = "en-US")
    }

    private fun createViewPager(){
        viewPagerAdapter = FilmListViewPagerAdapter(fragmentManager!!,categoryList)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun tabLayoutSelectedListener(){
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
                toolbarTitle = categoryList[tab.position].name.toString()
            }

        })
    }

    private fun observeViewModel() {
        val observer = Observer<State> { value ->
            value?.let {
                when (it) {
                    is State.Loading -> {
                        showLoading()
                    }

                    is State.Success<*> -> {
                        hideLoading()
                        renderModel(it.data!!)
                    }

                    is State.Error -> {
                        hideLoading()
                        DialogUtis.handleApiError(context,it.error,true)
                    }
                }
            }
        }
        viewModel.state.observe(this, observer)
    }

    private fun renderModel(model: Any) {
        if (model is CategoryResponse) {
            categoryList = emptyList()
            categoryList = model.categoryList
            createViewPager()
            tabLayoutSelectedListener()
        }
    }

}
