package com.dd.mdbc.ui.main

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.dd.mdbc.R
import com.dd.mdbc.databinding.DbDialogFragmentBinding
import kotlinx.android.synthetic.main.db_dialog_fragment.*

class DbDialogFragment(private var dbInfo: DbDialogViewModel.DBInfo) : DialogFragment() {

    private lateinit var viewModel: DbDialogViewModel
    private lateinit var binding: DbDialogFragmentBinding
    private lateinit var dialogListener: DialogListener

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DbDialogViewModel::class.java)
        viewModel.dbInfo = dbInfo
        binding.dbDialogViewModel = viewModel
        dialogListener = activity as DialogListener
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
        binding = DataBindingUtil.inflate(inflater, R.layout.db_dialog_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        db_dialog_toolbar!!.run {
            inflateMenu(R.menu.menu_add_db_fragment)
            setNavigationOnClickListener { dismiss() }
            setOnMenuItemClickListener {
                with(sharedPref.edit()) {
                    if (viewModel.dbInfo.dbName != "" && viewModel.dbInfo.connectionURI != "") {
                        if (viewModel.dbInfo.preferenceKey != "") {
                            remove(viewModel.dbInfo.preferenceKey)
                        }
                        putString(viewModel.dbInfo.dbName, viewModel.dbInfo.connectionURI)
                    }
                    commit()
                }
                dismiss()
                true
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDismiss(dialog: DialogInterface) {
        dialogListener.onDialogDismissed()
        super.onDismiss(dialog)
    }

    companion object {

        private const val TAG = "Add_DB_Fragment"

        interface DialogListener {
            fun onDialogDismissed()
        }

        fun display(
            fragmentManager: FragmentManager,
            dbInfo: DbDialogViewModel.DBInfo
        ): DbDialogFragment {
            val addDBFragment = DbDialogFragment(dbInfo)
            addDBFragment.show(fragmentManager, TAG)
            return addDBFragment
        }
    }
}