package ru.suleymanovtat.githubclient

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_details.*
import ru.suleymanovtat.githubclient.model.data.Item

class DetailsFragment : Fragment() {
    companion object {
        val KEY_ITEM = "key_item"
    }

    fun newInstance(item: Item): Fragment {
        val args = Bundle()
        args.putParcelable(KEY_ITEM, item)
        val fragment = DetailsFragment()
        fragment.setArguments(args)
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments!!.getParcelable<Item>(KEY_ITEM)
        tvUserLogin.setText(item.login)
        tvUserHtmlUrl.setText(item.html_url)
        Glide.with(this.activity!!)
                .load(item.avatar_url).apply(RequestOptions.circleCropTransform().error(R.drawable.ic_error))
                .into(avatar)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}
