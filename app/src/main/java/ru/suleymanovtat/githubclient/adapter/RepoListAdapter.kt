package ru.suleymanovtat.githubclient.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.suleymanovtat.githubclient.ItemViewModel
import ru.suleymanovtat.githubclient.databinding.ItemBinding
import ru.suleymanovtat.githubclient.model.data.Item

class RepoListAdapter(clickListener: ClickListener) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private var repoList: List<Item>? = arrayListOf()
    private var mClickListener = clickListener

    fun setRepoList(repoList: List<Item>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = repoList!![position]
        viewHolder.bind(item, mClickListener)
    }

    override fun getItemCount(): Int {
        return if (repoList == null) 0 else repoList!!.size
    }

    public class ViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var mBinding: ItemBinding = binding

        fun bind(item: Item, mClickListener: ClickListener) {
            mBinding.item = ItemViewModel(item)
            mBinding.root.setOnClickListener({
                mClickListener.onItemClick(item)
            })
            mBinding.executePendingBindings()
        }
    }
}