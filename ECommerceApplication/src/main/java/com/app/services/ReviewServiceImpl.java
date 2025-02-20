package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Product;
import com.app.entites.Review;
import com.app.entites.User;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.ReviewDTO;
import com.app.repositories.ProductRepo;
import com.app.repositories.ReviewRepo;
import com.app.repositories.UserRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepo reviewRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ReviewDTO createReview(String email, Long productId, Review content) {
		User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));;
		Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));;

		Review review = reviewRepo.findReviewByEmailAndProductId(email, productId);

		if (review != null) {
			throw new APIException("User with email " + email + " has already reviewed this product.");
		}

		Review newReview = new Review();


		newReview.setUser(user);
		newReview.setProduct(product);
		newReview.setContent(content.getContent());

		newReview = reviewRepo.save(newReview);

		return modelMapper.map(newReview, ReviewDTO.class);
	}

	// @Override
	// public BrandResponse getBrands(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
	// 	Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
	// 			: Sort.by(sortBy).descending();

	// 	Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		
	// 	Page<Brand> pageBrands = brandRepo.findAll(pageDetails);

	// 	List<Brand> brands = pageBrands.getContent();

	// 	if (brands.size() == 0) {
	// 		throw new APIException("No brand is created till now");
	// 	}

	// 	List<BrandDTO> brandDTOs = brands.stream()
	// 			.map(brand -> modelMapper.map(brand, BrandDTO.class)).collect(Collectors.toList());

	// 	BrandResponse brandResponse = new BrandResponse();
		
	// 	brandResponse.setContent(brandDTOs);
	// 	brandResponse.setPageNumber(pageBrands.getNumber());
	// 	brandResponse.setPageSize(pageBrands.getSize());
	// 	brandResponse.setTotalElements(pageBrands.getTotalElements());
	// 	brandResponse.setTotalPages(pageBrands.getTotalPages());
	// 	brandResponse.setLastPage(pageBrands.isLast());
		
	// 	return brandResponse;
	// }

	@Override
	public ReviewDTO updateReview(String email, Long productId, Review content) {
		userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));;
		productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));;

		Review review = reviewRepo.findReviewByEmailAndProductId(email, productId);

		if (review == null) {
			throw new APIException("User with email " + email + " has not yet reviewed this product.");
		}

		review.setContent(content.getContent());

		return modelMapper.map(review, ReviewDTO.class);
	}

	@Override
	public  String deleteReview(String email, Long productId) {
		userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));;
		productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));;

		Review review = reviewRepo.findReviewByEmailAndProductId(email, productId);

		if (review == null) {
			throw new APIException("User with email " + email + " has not yet reviewed this product.");
		}

		reviewRepo.delete(review);

		return "Review deleted successfully !!!";
	}

}
