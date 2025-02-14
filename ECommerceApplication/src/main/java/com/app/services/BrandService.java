package com.app.services;

import com.app.entites.Brand;
import com.app.payloads.BrandDTO;
import com.app.payloads.BrandResponse;

public interface BrandService {

    BrandDTO createBrand(Brand brand);

    BrandDTO updateBrand(Brand brand, Long brandId);
    
	BrandResponse getBrands(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	String deleteBrand(Long brandId);
}

