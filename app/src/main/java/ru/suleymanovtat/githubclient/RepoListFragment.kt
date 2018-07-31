package ru.suleymanovtat.githubclient

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_repo_list.*
import ru.suleymanovtat.githubclient.adapter.ClickListener
import ru.suleymanovtat.githubclient.adapter.RepoListAdapter
import ru.suleymanovtat.githubclient.model.api.ApiModule
import ru.suleymanovtat.githubclient.model.data.Item
import ru.suleymanovtat.githubclient.model.data.ItemResponse

class RepoListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mAdapter: RepoListAdapter
    private val disposable = CompositeDisposable()
    private var items: List<Item> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = RepoListAdapter(mOnClickListener)
        recyclerList.adapter = mAdapter
        refresher.setOnRefreshListener(this)
        if (items.isEmpty()) {
            onRefresh()
        } else {
            refresher.isRefreshing = false
            recyclerList.visibility = View.VISIBLE
            mAdapter.setRepoList(items)
        }
    }

    override fun onRefresh() {
        refresher.post { queryRepo() }
    }

    private fun queryRepo() {
        disposable.add(ApiModule.apiInterface.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    errorView.visibility = View.GONE
                    refresher.isRefreshing = true
                }
                .doFinally {
                    refresher.isRefreshing = false
                    recyclerList.visibility = View.VISIBLE
                }
                .subscribe({ t: ItemResponse? ->
                    items = t!!.items!!
                    mAdapter.setRepoList(t.items!!)
                }) { throwable: Throwable? ->
                    if (ApiModule.NETWORK_EXCEPTIONS.contains(throwable!!::class.java)) {
                        Toast.makeText(activity, getString(R.string.check_network), Toast.LENGTH_LONG).show()
                        if (mAdapter.itemCount == 0)
                            errorView.visibility = View.VISIBLE
                        return@subscribe
                    }
                    errorView.visibility = View.VISIBLE
                    recyclerList.visibility = View.GONE
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onPause() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
        super.onPause()
    }

    private val mOnClickListener = ClickListener { position ->
        val item = items[position]
        activity!!.supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fl_container,
                        DetailsFragment().newInstance(item), DetailsFragment::class.toString()).addToBackStack(null).commit()
    }
}


