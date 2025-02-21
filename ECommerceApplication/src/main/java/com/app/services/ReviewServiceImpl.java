package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import com.app.entites.Product;
import com.app.entites.Review;
import com.app.entites.User;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.ReviewDTO;
import com.app.payloads.ReviewResponse;
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

	@Override
	public ReviewResponse getAllReviews(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		
		Page<Review> pageReview = reviewRepo.findAll(pageDetails);

		List<Review> reviews = pageReview.getContent();

		if (reviews.size() == 0) {
			throw new APIException("No review is created till now");
		}

		List<ReviewDTO> reviewDTOs = reviews.stream()
				.map(brand -> modelMapper.map(brand, ReviewDTO.class)).collect(Collectors.toList());

		ReviewResponse reviewResponse = new ReviewResponse();
		
		reviewResponse.setContent(reviewDTOs);
		reviewResponse.setPageNumber(pageReview.getNumber());
		reviewResponse.setPageSize(pageReview.getSize());
		reviewResponse.setTotalElements(pageReview.getTotalElements());
		reviewResponse.setTotalPages(pageReview.getTotalPages());
		reviewResponse.setLastPage(pageReview.isLast());
		
		return reviewResponse;
	}


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

	@Override
	public ReviewResponse getReviewByUser(String email, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		
		Page<Review> pageReview = reviewRepo.findByUser(user, pageDetails);

		List<Review> reviews = pageReview.getContent();

		if (reviews.size() == 0) {
			throw new APIException("No review is created till now");
		}

		List<ReviewDTO> reviewDTOs = reviews.stream()
				.map(brand -> modelMapper.map(brand, ReviewDTO.class)).collect(Collectors.toList());

		ReviewResponse reviewResponse = new ReviewResponse();
		
		reviewResponse.setContent(reviewDTOs);
		reviewResponse.setPageNumber(pageReview.getNumber());
		reviewResponse.setPageSize(pageReview.getSize());
		reviewResponse.setTotalElements(pageReview.getTotalElements());
		reviewResponse.setTotalPages(pageReview.getTotalPages());
		reviewResponse.setLastPage(pageReview.isLast());
		
		return reviewResponse;
	}
	@Override
	public ReviewResponse getReviewByProduct(Long productId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {
		
		Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		
		Page<Review> pageReview = reviewRepo.findByProduct(product, pageDetails);

		List<Review> reviews = pageReview.getContent();

		if (reviews.size() == 0) {
			throw new APIException("No review is created till now");
		}

		List<ReviewDTO> reviewDTOs = reviews.stream()
				.map(brand -> modelMapper.map(brand, ReviewDTO.class)).collect(Collectors.toList());

		ReviewResponse reviewResponse = new ReviewResponse();
		
		reviewResponse.setContent(reviewDTOs);
		reviewResponse.setPageNumber(pageReview.getNumber());
		reviewResponse.setPageSize(pageReview.getSize());
		reviewResponse.setTotalElements(pageReview.getTotalElements());
		reviewResponse.setTotalPages(pageReview.getTotalPages());
		reviewResponse.setLastPage(pageReview.isLast());
		
		return reviewResponse;
	}

}
