package com.example.ecommerceapplication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.ecommerceapplication.data.model.Product
import com.example.ecommerceapplication.databinding.FragmentHomeBinding
import com.example.ecommerceapplication.ui.adapter.ProductAdapter
import com.example.ecommerceapplication.ui.viewmodel.ProductViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.getProducts().observe(viewLifecycleOwner, Observer { products: List<Product>? ->
            products?.let {
                binding.recyclerViewProducts.adapter = ProductAdapter(it) { product: Product ->
                    displayProductDetails(product.id)
                }
            }
        })
    }

    private fun displayProductDetails(productId: Int) {
        viewModel.getProduct(productId).observe(viewLifecycleOwner, Observer { product: Product? ->
            product?.let {
                // Update the layout to display product details
                binding.recyclerViewProducts.visibility = View.GONE
                binding.productDetailsLayout.visibility = View.VISIBLE

                Glide.with(this)
                    .load(it.image)
                    .into(binding.imageViewProductDetail)

                binding.textViewTitleDetail.text = it.title
                binding.textViewPriceDetail.text = "$${it.price}"
                binding.textViewDescriptionDetail.text = it.description
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
