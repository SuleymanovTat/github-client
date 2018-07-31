package ru.suleymanovtat.githubclient.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.suleymanovtat.githubclient.R
import ru.suleymanovtat.githubclient.model.data.Item

class RepoListAdapter(private val listener: ClickListener) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private var repoList: List<Item>? = arrayListOf()

    fun setRepoList(repoList: List<Item>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.repo_layout, viewGroup, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = repoList!![position]
        viewHolder.name.text = item.login
        Glide.with(viewHolder.avatar.context)
                .load(item.avatar_url).apply(RequestOptions.circleCropTransform().error(R.drawable.ic_error))
                .into(viewHolder.avatar)
        viewHolder.itemView.setOnClickListener { listener.onItemClick(position) }
    }

    override fun getItemCount(): Int {
        return if (repoList == null) 0 else repoList!!.size
    }

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView
        val avatar: ImageView

        init {
            name = itemView.findViewById(R.id.tv_name)
            avatar = itemView.findViewById(R.id.avatar)
        }
    }
}