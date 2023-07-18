package com.example.newsapp_praxisphase.models

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("articles")
    var articles : MutableList<Article>,

            @SerializedName("status")
            var status : String,
                    @SerializedName("totalResults")
                    var totalResults : Int? ,
)