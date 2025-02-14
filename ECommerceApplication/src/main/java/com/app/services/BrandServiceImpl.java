package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.entites.Brand;
import com.app.entites.Category;
import com.app.entites.Product;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.BrandDTO;
import com.app.payloads.CategoryDTO;
import com.app.payloads.CategoryResponse;
import com.app.repositories.BrandRepo;
import com.app.repositories.CategoryRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepo brandRepo;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BrandDTO createBrand(Brand brand) {
        Brand savedBrand = brandRepo.findByBrandName(brand.getBrandName());
        if (savedBrand != null) {
			throw new APIException("Brand with the name '" + brand.getBrandName() + "' already exists !!!");

        }

        savedBrand = brandRepo.save(brand);

		return modelMapper.map(savedBrand, BrandDTO.class);
	}

	// @Override
	// public CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
	// 	Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
	// 			: Sort.by(sortBy).descending();

	// 	Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		
	// 	Page<Category> pageCategories = categoryRepo.findAll(pageDetails);

	// 	List<Category> categories = pageCategories.getContent();

	// 	if (categories.size() == 0) {
	// 		throw new APIException("No category is created till now");
	// 	}

	// 	List<CategoryDTO> categoryDTOs = categories.stream()
	// 			.map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());

	// 	CategoryResponse categoryResponse = new CategoryResponse();
		
	// 	categoryResponse.setContent(categoryDTOs);
	// 	categoryResponse.setPageNumber(pageCategories.getNumber());
	// 	categoryResponse.setPageSize(pageCategories.getSize());
	// 	categoryResponse.setTotalElements(pageCategories.getTotalElements());
	// 	categoryResponse.setTotalPages(pageCategories.getTotalPages());
	// 	categoryResponse.setLastPage(pageCategories.isLast());
		
	// 	return categoryResponse;
	// }

	@Override
	public BrandDTO updateBrand (Brand brand, Long brandId) {
        Brand savedBrand = brandRepo.findById(brandId)
            .orElseThrow(() -> new ResourceNotFoundException("Brand", "brandId", brandId));

        brand.setBrandId(brandId);

        savedBrand = brandRepo.save(brand);

		return modelMapper.map(savedBrand, BrandDTO.class);
	}

	// @Override
	// public String deleteCategory(Long categoryId) {
	// 	Category category = categoryRepo.findById(categoryId)
	// 			.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
	// 	List<Product> products = category.getProducts();

	// 	products.forEach(product -> {
	// 		productService.deleteProduct(product.getProductId());
	// 	});
		
	// 	categoryRepo.delete(category);

	// 	return "Category with categoryId: " + categoryId + " deleted successfully !!!";
	// }

}
