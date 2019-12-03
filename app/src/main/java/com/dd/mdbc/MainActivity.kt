package com.dd.mdbc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dd.mdbc.ui.main.AddDBFragment
import com.dd.mdbc.ui.main.MainFragment

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

    fun openDialog(view: View) {
        AddDBFragment.display(supportFragmentManager)
    }

}
