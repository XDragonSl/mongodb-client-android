package com.dd.mdbc.ui.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.dd.mdbc.BR

class AddDbViewModel : ViewModel() {

    class DBInfo : BaseObservable() {

        @get:Bindable
        var dbName: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.adbViewModel)
            }
        @get:Bindable
        var connectionURI: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.adbViewModel)
            }
    }

    val dbInfo = DBInfo()
}
