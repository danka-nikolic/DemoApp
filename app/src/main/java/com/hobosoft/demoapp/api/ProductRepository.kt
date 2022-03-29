package com.hobosoft.demoapp.api

import com.hobosoft.demoapp.model.Page
import com.hobosoft.demoapp.model.Product
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductRepository @Inject internal constructor(private val productApi: ProductApi) {

    fun getProductPage(page: Int, size: Int = 20): Single<Page<Product>> {
        return productApi.getProductPage(page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(10, TimeUnit.SECONDS)
    }

}