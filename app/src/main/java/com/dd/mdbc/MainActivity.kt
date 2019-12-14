package com.dd.mdbc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.dd.mdbc.ui.main.AddDBFragment
import com.dd.mdbc.ui.main.MainFragment

class MainActivity : AppCompatActivity(), AddDBFragment.Companion.DialogListener {

    lateinit var mainFragment: MainFragment

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

    override fun onDialogDismissed(dialogFragment: DialogFragment) {
        mainFragment.onDialogDismissed(dialogFragment)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.add_button -> {
                AddDBFragment.display(supportFragmentManager)
            }
            R.id.db_card -> {
            }
            R.id.db_edit -> {
            }
            R.id.db_remove -> {
            }
        }
    }
}
