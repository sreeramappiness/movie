//package com.victoriasecret.movie.utils
//
//import android.app.Dialog
//import android.view.View
//import android.view.Window
//import android.widget.TextView
//import com.victoriasecret.movie.BaseActivity
//import com.victoriasecret.movie.R
//
//class LoadingDialog() {
//    private lateinit var dialog: Dialog
//    private lateinit var tvForTitle___t: TextView
//
//    constructor(context: BaseActivity) : this() {
//        val dialogView = View.inflate(context, R.layout.app_loader, null)
//        dialog = Dialog(context, R.style.LoadingDialog)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(dialogView)
//        tvForTitle___t = dialog.findViewById(R.id.tvForTitle___t)
//        tvForTitle___t.visibility = View.GONE
//        dialog.setCancelable(false)
//    }
//
//    private fun startAnim() {
//        if (!dialog.isShowing)
//            avi.show()
//        dialog.show()
//    }
//
//    private fun stopAnim() {
//        if (dialog.isShowing)
//            dialog.dismiss()
//        avi.hide()
//    }
//
//    fun setLoading(isLoading: Boolean, message: String) {
//        if (isLoading)
//            startAnim()
//        else
//            stopAnim()
//    }
//}