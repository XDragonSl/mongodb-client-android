package com.dd.mdbc.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.dd.mdbc.R
import com.dd.mdbc.databinding.AddDbFragmentBinding
import kotlinx.android.synthetic.main.dialog_toolbar.*

class AddDBFragment : DialogFragment() {

    private lateinit var viewModel: AddDbViewModel
    private lateinit var binding: AddDbFragmentBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddDbViewModel::class.java)
        binding.adbViewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.run {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setWindowAnimations(R.style.AppTheme_DialogAnimation)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.add_db_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        dialog_toolbar!!.run {
            inflateMenu(R.menu.menu_add_db_fragment)
            setNavigationOnClickListener { dismiss() }
            setOnMenuItemClickListener {
                with(sharedPref.edit()) {
                    putString(viewModel.dbInfo.dbName, viewModel.dbInfo.connectionURI)
                    commit()
                }
                dismiss()
                true
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        private const val TAG = "Add_DB_Fragment"

        fun display(fragmentManager: FragmentManager): AddDBFragment {
            val addDBFragment = AddDBFragment()
            addDBFragment.show(fragmentManager, TAG)
            return addDBFragment
        }
    }
}