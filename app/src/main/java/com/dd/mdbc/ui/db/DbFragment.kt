package com.dd.mdbc.ui.db

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.mdbc.R
import com.dd.mdbc.adapters.CollectionAdapter
import com.dd.mdbc.databinding.DbFragmentBinding
import com.dd.mdbc.services.CollectionService
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.db_fragment.*
import kotlinx.android.synthetic.main.progress_indicator.*

class DbFragment(private var name: String, private var uri: String) : Fragment() {

    companion object {
        fun newInstance(name: String, uri: String) = DbFragment(name, uri)
    }

    private lateinit var viewModel: DbViewModel
    private lateinit var binding: DbFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.db_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DbViewModel::class.java)
        viewModel.title = name
        binding.dbViewModel = viewModel
        recycler_view.layoutManager = LinearLayoutManager(activity)
    }

    override fun onResume() {
        loadCollections()
        super.onResume()
    }

    private fun loadCollections() {
        val collectionService = CollectionService.create()
        collectionService.load(uri)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                progress_indicator.visibility = View.INVISIBLE
                recycler_view.adapter = CollectionAdapter(
                    result,
                    R.layout.collection_item,
                    context!!
                )
            }, {
                progress_indicator.visibility = View.INVISIBLE
                Snackbar.make(recycler_view, R.string.error, Snackbar.LENGTH_SHORT).show();
            })
    }
}
