package com.majid.model

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    @SerializedName("Status") val status : Int,
    @SerializedName("errors") val errors : List<Errors>
) {

    data class Errors (

        @SerializedName("key") val key : String,
        @SerializedName("error") val error : String
    )
}