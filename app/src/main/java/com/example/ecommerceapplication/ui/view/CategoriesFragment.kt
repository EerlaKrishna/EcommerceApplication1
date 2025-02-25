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
import com.example.ecommerceapplication.databinding.FragmentCategoriesBinding
import com.example.ecommerceapplication.ui.adapter.CategoryAdapter
import com.example.ecommerceapplication.ui.adapter.ProductAdapter
import com.example.ecommerceapplication.ui.viewmodel.ProductViewModel

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewCategories.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.getCategories().observe(viewLifecycleOwner, Observer { categories: List<String>? ->
            categories?.let {
                binding.recyclerViewCategories.adapter = CategoryAdapter(it) { category: String ->
                    displayProductsByCategory(category)
                }
            }
        })
    }

    private fun displayProductsByCategory(category: String) {
        viewModel.getProductsByCategory(category).observe(viewLifecycleOwner, Observer { products: List<Product>? ->
            products?.let {
                binding.recyclerViewCategories.adapter = ProductAdapter(it) { product: Product ->
                    displayProductDetails(product)
                }
            }
        })
    }

    private fun displayProductDetails(product: Product) {
        // Update the layout to display product details
        binding.recyclerViewCategories.visibility = View.GONE
        binding.productDetailsLayout.visibility = View.VISIBLE

        Glide.with(this)
            .load(product.image)
            .into(binding.imageViewProductDetail)

        binding.textViewTitleDetail.text = product.title
        binding.textViewPriceDetail.text = "$${product.price}"
        binding.textViewDescriptionDetail.text = product.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
