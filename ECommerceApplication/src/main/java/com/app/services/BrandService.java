package com.app.services;

import com.app.entites.Brand;
import com.app.payloads.BrandDTO;

public interface BrandService {

    BrandDTO createBrand(Brand brand);

    BrandDTO updateBrand(Brand brand, Long brandId);
    
	// CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	// String deleteCategory(Long categoryId);
}

