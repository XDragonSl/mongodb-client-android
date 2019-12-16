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
import com.dd.mdbc.adapters.DocumentAdapter
import com.dd.mdbc.databinding.CollectionFragmentBinding
import com.dd.mdbc.services.DocumentService
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.collection_fragment.*
import kotlinx.android.synthetic.main.progress_indicator.*

class CollectionFragment(private var db: String, private var collection: String, private var uri: String) : Fragment() {

    companion object {
        fun newInstance(db: String, collection: String, uri: String) = CollectionFragment(db, collection, uri)
    }

    private lateinit var viewModel: CollectionViewModel
    private lateinit var binding: CollectionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.collection_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CollectionViewModel::class.java)
        binding.collectionViewModel = viewModel
        viewModel.title = "$db: $collection"
        recycler_view.layoutManager = LinearLayoutManager(activity)
    }

    override fun onResume() {
        loadDocuments()
        super.onResume()
    }

    private fun loadDocuments() {
        val apiService = DocumentService.create()
        apiService.load(uri, collection)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                progress_indicator.visibility = View.INVISIBLE
                recycler_view.adapter = DocumentAdapter(
                    result,
                    R.layout.document_item,
                    context!!
                )
            }, {
                progress_indicator.visibility = View.INVISIBLE
                Snackbar.make(recycler_view, R.string.error, Snackbar.LENGTH_SHORT).show();
            })
    }
}
