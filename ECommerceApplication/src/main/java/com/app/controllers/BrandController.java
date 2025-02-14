package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.AppConstants;
import com.app.entites.Brand;
import com.app.entites.Category;
import com.app.payloads.BrandDTO;
import com.app.payloads.CategoryDTO;
import com.app.payloads.CategoryResponse;
import com.app.services.BrandService;
import com.app.services.CategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@PostMapping("/admin/brand")
	public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody Brand brand) {
        BrandDTO savedBrandDTO = brandService.createBrand(brand);

		return new ResponseEntity<BrandDTO>(savedBrandDTO, HttpStatus.CREATED);
	}

	// @GetMapping("/public/categories")
	// public ResponseEntity<CategoryResponse> getCategories(
	// 		@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
	// 		@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
	// 		@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
	// 		@RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
		
	// 	CategoryResponse categoryResponse = categoryService.getCategories(pageNumber, pageSize, sortBy, sortOrder);

	// 	return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.FOUND);
	// }

	@PutMapping("/admin/brands/{brandId}")
	public ResponseEntity<BrandDTO> updateBrand(@RequestBody Brand brand,
			@PathVariable Long brandId) {
        BrandDTO brandDTO = brandService.updateBrand(brand, brandId);

		return new ResponseEntity<BrandDTO>(brandDTO, HttpStatus.OK);
	}

	// @DeleteMapping("/admin/categories/{categoryId}")
	// public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
	// 	String status = categoryService.deleteCategory(categoryId);

	// 	return new ResponseEntity<String>(status, HttpStatus.OK);
	// }

}
