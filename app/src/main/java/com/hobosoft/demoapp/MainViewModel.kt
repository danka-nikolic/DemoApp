package com.hobosoft.demoapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hobosoft.demoapp.api.ProductRepository
import com.hobosoft.demoapp.model.Page
import com.hobosoft.demoapp.model.Product
import com.hobosoft.demoapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    val productListLiveData: MutableLiveData<Result<Page<Product>>?> = MutableLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getProductPage(page: Int, size: Int = 20) {
        productListLiveData.postValue(Result.Loading())
        val disposable = productRepository.getProductPage(page, size)
            .subscribe(
                { response ->
                    productListLiveData.postValue(Result.Success(response))
                },
                { error ->
                    val message = error.message ?: "UNKNOWN_ERROR"
                    productListLiveData.postValue(Result.Error(message))
                }
            )

        compositeDisposable.add(disposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}