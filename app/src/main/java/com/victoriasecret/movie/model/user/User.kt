package com.victoriasecret.movie.model.user

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("statusCode" ) var statusCode : Int?                  = null,
    @SerializedName("status"     ) var status     : Boolean?              = null,
    @SerializedName("dataObject" ) var dataObject : ArrayList<DataObject> = arrayListOf()

)