package com.dd.mdbc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dd.mdbc.ui.db.DbFragment

class DbActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    DbFragment.newInstance(
                        intent.getStringExtra(DB_NAME)!!,
                        intent.getStringExtra(CONNECTION_URI)!!
                    )
                )
                .commitNow()
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.db_card -> {
            }
        }
    }
}
