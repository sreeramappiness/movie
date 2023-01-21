package com.victoriasecret.movie.model.details

import com.google.gson.annotations.SerializedName

data class Networks (

    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("name"           ) var name          : String? = null,
    @SerializedName("logo_path"      ) var logoPath      : String? = null,
    @SerializedName("origin_country" ) var originCountry : String? = null

)
