package com.dd.mdbc.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.dd.mdbc.R
import kotlinx.android.synthetic.main.dialog_toolbar.*

class AddDBFragment : DialogFragment() {

    private lateinit var viewModel: AddDbViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddDbViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
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
        return inflater.inflate(R.layout.add_db_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog_toolbar!!.run {
            inflateMenu(R.menu.menu_add_db_fragment)
            setNavigationOnClickListener { dismiss() }
            setOnMenuItemClickListener {
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