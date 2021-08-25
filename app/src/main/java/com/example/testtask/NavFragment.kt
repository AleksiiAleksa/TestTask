package com.example.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.PaginationListener.Companion.PAGE_START
import kotlinx.android.synthetic.main.fragment_nav.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Handler
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testtask.Retrofit.Common
import com.example.testtask.Retrofit.RetrofitServices


class NavFragment :  Fragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var rService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter
    var context: String? = null
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    private var currentPage: Int = PAGE_START
    private val totalPage = 10
    var itemCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_nav, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rService = Common.retrofitService
        swipeRefreshLayout.setOnRefreshListener(this@NavFragment);
        recyclerList.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(getActivity())
        recyclerList.setLayoutManager(layoutManager)
        adapter = RecyclerAdapter(ArrayList())

        recyclerList.adapter = adapter
        doApiCall()
        recyclerList.addOnScrollListener(object : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                this@NavFragment.isLoading = true
                currentPage++
                doApiCall()
            }

            override val isLastPage: Boolean
                get() =  this@NavFragment.isLastPage
            override val isLoading: Boolean
                get() =   this@NavFragment.isLoading

        })

    }

    private fun doApiCall() {
        rService.getRecords().enqueue(object : Callback<MutableList<Data>> {
            override fun onFailure(call: Call<MutableList<Data>>, t: Throwable) {

            }
            override fun onResponse(call: Call<MutableList<Data>>, response: Response<MutableList<Data>>) {
                val items: ArrayList<Data> = ArrayList()
                val allData:MutableList<Data> = response.body() as MutableList<Data>
                Handler().postDelayed(Runnable {
                    for (i in 0..9) {
                        itemCount++
                        if(itemCount<allData.size)
                            items.add(allData[itemCount])

                    }
                    if (currentPage !== PAGE_START) adapter.removeLoading()
                    adapter.addItems(items)
                    swipeRefreshLayout.setRefreshing(false)

                    if (currentPage < totalPage) {
                        adapter.addLoading()
                    } else {
                        isLastPage = true
                    }
                    isLoading = false
                }, 1500)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onRefresh() {
        itemCount = 0
        currentPage = PAGE_START
        isLastPage = false
        adapter.clear()
        doApiCall()
    }
}


