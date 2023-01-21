package com.victoriasecret.movie

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import com.victoriasecret.movie.utils.LoadingDialog

open class BaseActivity : AppCompatActivity() {

    private var sActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sActivity = this
        checkInternet()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun checkInternet() {
        if (!isNetworkAvailable(this)){
            snackBarToast("Please check your internet")
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    fun snackBarToast(message: String?) {
        val toast = Toast.makeText(sActivity, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, 0, 250)
        toast.show()
    }

}