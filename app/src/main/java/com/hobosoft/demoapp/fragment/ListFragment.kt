package com.hobosoft.demoapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hobosoft.demoapp.MainViewModel
import com.hobosoft.demoapp.databinding.FragmentListBinding
import com.hobosoft.demoapp.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productListLiveData.observe(this, { result ->
            when (result) {
                is Result.Success -> {
                    val data = result.data
                    data?.list?.forEach {
                        Log.e("TAG", it.name)
                    }
                    hideProgress()
                }
                is Result.Loading -> {
                    showProgress()
                }
                is Result.Error -> {
                    hideProgress()
                    Log.e("TAG", "Error fetching products: ${result.errorMessage}")
                }
            }
        })

        binding.button.setOnClickListener {
            viewModel.getProductPage(0, 20)
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }
}