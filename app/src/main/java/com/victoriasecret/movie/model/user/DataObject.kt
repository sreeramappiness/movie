package com.victoriasecret.movie.model.user

import com.google.gson.annotations.SerializedName

data class DataObject (

    @SerializedName("userName" ) var userName : String? = null,
    @SerializedName("password" ) var password : String? = null

)
