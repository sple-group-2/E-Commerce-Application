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

import com.app.payloads.ReviewDTO;
import com.app.payloads.ReviewResponse;
import com.app.services.ReviewService;
import com.app.config.AppConstants;
import com.app.entites.Review;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class ReviewController {
	
	@Autowired
	public ReviewService reviewService;
	
	@PostMapping("/public/users/{email}/products/{productId}/review")
	public ResponseEntity<ReviewDTO> reviewProducts(@PathVariable String email, @PathVariable Long productId, @RequestBody Review content) {
        ReviewDTO review = reviewService.createReview(email, productId, content);
		
		return new ResponseEntity<ReviewDTO>(review, HttpStatus.CREATED);
	}

    // TODO get belom

    @PutMapping("/public/users/{email}/products/{productId}/updateReview")
	public ResponseEntity<ReviewDTO> updateReview(@PathVariable String email, @PathVariable Long productId, @RequestBody Review content) {
        ReviewDTO review = reviewService.updateReview(email, productId, content);
		
		return new ResponseEntity<ReviewDTO>(review, HttpStatus.CREATED);
	}

    @DeleteMapping("/public/users/{email}/products/{productId}/deleteReview")
	public ResponseEntity<String> deleteBrand(@PathVariable String email, @PathVariable Long productId) {
        String status = reviewService.deleteReview(email, productId);

		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@GetMapping("/admin/reviews")
	public ResponseEntity<ReviewResponse> getAllReviews(
			@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_REVIEWS_BY, required = false) String sortBy,
			@RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
		
		ReviewResponse reviewResponse = reviewService.getAllReviews(pageNumber, pageSize, sortBy, sortOrder);

		return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.FOUND);
	}

	@GetMapping("/public/users/{email}/reviews")
	public ResponseEntity<ReviewResponse> getReviewByUser(
			@PathVariable String email,
			@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_REVIEWS_BY, required = false) String sortBy,
			@RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
		
		ReviewResponse reviewResponse = reviewService.getReviewByUser(email, pageNumber, pageSize, sortBy, sortOrder);

		return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.FOUND);
	}

	@GetMapping("/public/products/{productId}/reviews")
	public ResponseEntity<ReviewResponse> getReviewByProduct(
			@PathVariable Long productId,
			@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_REVIEWS_BY, required = false) String sortBy,
			@RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
		
		ReviewResponse reviewResponse = reviewService.getReviewByProduct(productId, pageNumber, pageSize, sortBy, sortOrder);

		return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.FOUND);
	}
	
}
