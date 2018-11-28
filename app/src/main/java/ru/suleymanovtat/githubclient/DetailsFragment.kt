package ru.suleymanovtat.githubclient

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_details.*
import ru.suleymanovtat.githubclient.databinding.ItemDetailsBinding
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
        val binding: ItemDetailsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_details, container, false)
        val view: View = binding.root
        val item = arguments!!.getParcelable<Item>(KEY_ITEM)
        binding.item = ItemViewModel(item)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}
