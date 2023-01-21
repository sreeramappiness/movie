package com.victoriasecret.movie.views

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.victoriasecret.movie.BaseActivity
import com.victoriasecret.movie.R
import com.victoriasecret.movie.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setViews()
        setViewModel()
        registerObservers()
    }

    private fun setViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun setViews() {
        etUserName.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                userNameHint.visibility = View.VISIBLE
            } else {
                userNameHint.visibility = View.INVISIBLE
            }
            validateData()
        }
        etPassword.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                etPasswordHint.visibility = View.VISIBLE
            } else {
                etPasswordHint.visibility = View.INVISIBLE
            }
            validateData()
        }
        ivShowPassword.setOnClickListener {
            ivHidePassword.visibility = View.VISIBLE
            ivShowPassword.visibility = View.GONE
            etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        ivHidePassword.setOnClickListener {
            ivHidePassword.visibility = View.GONE
            ivShowPassword.visibility = View.VISIBLE
            etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        btnLogin.setOnClickListener {
            if(btnLogin.alpha == 1F){
                loader.visibility = View.VISIBLE
                mainViewModel.getUsers()
            }
        }
    }

    private fun login() {
        val intent = Intent(this, PopularMovieActivity::class.java)
        startActivity(intent)
    }

    private fun validateData() {
        if (etUserName.text.isNotEmpty() && etPassword.text.isNotEmpty()){
            btnLogin.alpha = 1F
            login_movie.alpha = 1F
        }else {
            btnLogin.alpha = 0.7F
            login_movie.alpha = 0.7F
        }
    }

    private fun registerObservers() {

        mainViewModel.usersSuccessLiveData.observe(this, Observer { userList ->

            userList?.let {
                loader.visibility = View.GONE
                if(userList.statusCode?.equals(200)!!&& userList.dataObject[0].userName?.equals(etUserName.text.toString())!!
                    && userList.dataObject[0].password?.equals(etPassword.text.toString())!!){
                    login()
                }else{
                    snackBarToast(this.getString(R.string.invalid))
                }

            }
        })

        mainViewModel.usersFailureLiveData.observe(this, Observer { isFailed ->

            isFailed?.let {
                loader.visibility = View.GONE
                Toast.makeText(this, this.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        })

    }
}