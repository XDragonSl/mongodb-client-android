package com.dd.mdbc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dd.mdbc.ui.main.DbDialogFragment
import com.dd.mdbc.ui.main.DbDialogViewModel
import com.dd.mdbc.ui.main.MainFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.db_item.view.*

const val DB_NAME = "DB_NAME"
const val CONNECTION_URI = "CONNECTION_URI"

class MainActivity : AppCompatActivity(), DbDialogFragment.Companion.DialogListener {

    private lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            mainFragment = MainFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
        }
    }

    override fun onDialogDismissed() {
        mainFragment.onDialogDismissed()
    }

    fun onClick(view: View) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        when (view.id) {
            R.id.add_button -> {
                DbDialogFragment.display(supportFragmentManager, DbDialogViewModel.DBInfo())
            }
            R.id.db_card -> {
                val intent = Intent(this, DbActivity::class.java).apply {
                    putExtra(DB_NAME, view.title.text.toString())
                    putExtra(CONNECTION_URI, sharedPref.getString(view.title.text.toString(), ""))
                }
                startActivity(intent)
            }
            R.id.db_edit -> {
                val dbInfo = DbDialogViewModel.DBInfo()
                dbInfo.preferenceKey = (view.parent as View).title.text.toString()
                dbInfo.dbName = dbInfo.preferenceKey
                dbInfo.connectionURI = sharedPref.getString(dbInfo.preferenceKey, "")!!
                DbDialogFragment.display(supportFragmentManager, dbInfo)
            }
            R.id.db_remove -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.confirm_title)
                    .setMessage(R.string.confirm_message)
                    .setPositiveButton(R.string.confirm_ok) { dialog, _ ->
                        with(sharedPref.edit()) {
                            remove((view.parent as View).title.text.toString())
                            apply()
                        }
                        dialog.dismiss()
                        mainFragment.onDialogDismissed()
                    }
                    .setNegativeButton(R.string.confirm_cancel) { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }
        }
    }
}
