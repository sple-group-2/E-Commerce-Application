package com.app.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.entites.Product;
import com.app.payloads.ProductDTO;
import com.app.payloads.ProductResponse;

public interface ProductService {

	ProductDTO addProduct(Long categoryId, Product product, Long brandId, String couponName);

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

	ProductDTO updateProduct(Long productId, Product product);

	ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

	ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

	String deleteProduct(Long productId);

	ProductResponse searchProductByBrandName(String brandName, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);
	
	ProductResponse searchProductByBrandId(Long brandId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

	ProductResponse searchProductByCouponName(String couponName, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

}
