package com.dd.mdbc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dd.mdbc.ui.main.AddDBFragment
import com.dd.mdbc.ui.main.MainFragment
import kotlinx.android.synthetic.main.db_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.add_button -> {
                AddDBFragment.display(supportFragmentManager)
            }
            R.id.db_card -> {
                Log.i("SelectButton: ", view.title.text.toString())
            }
            R.id.db_edit -> {
                Log.i("EditButton: ", (view.parent as View).title.text.toString())
            }
            R.id.db_remove -> {
                Log.i("RemoveButton: ", (view.parent as View).title.text.toString())
            }
        }
    }
}
