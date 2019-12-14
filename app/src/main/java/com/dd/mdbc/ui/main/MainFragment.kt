package com.dd.mdbc.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.mdbc.DbAdapter
import com.dd.mdbc.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), DbDialogFragment.Companion.DialogListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        recycler_view.layoutManager = LinearLayoutManager(activity)
    }

    override fun onResume() {
        loadDBs()
        super.onResume()
    }

    override fun onDialogDismissed() {
        loadDBs()
    }

    private fun loadDBs() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        recycler_view.adapter = DbAdapter(sharedPref.all.toList(), R.layout.db_item, context!!)
    }
}
