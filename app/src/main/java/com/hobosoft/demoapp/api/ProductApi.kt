package com.hobosoft.demoapp.api

import com.hobosoft.demoapp.model.Page
import com.hobosoft.demoapp.model.Product
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/products")
    fun getProductPage(@Query("page") page: Int, @Query("size") size: Int = 20): Single<Page<Product>>

}