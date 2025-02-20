package com.app.services;

import com.app.entites.Review;
import com.app.payloads.ReviewDTO;
import com.app.payloads.ReviewResponse;

public interface ReviewService {

    ReviewDTO createReview(String email, Long productId, Review content);

    ReviewDTO updateReview(String email, Long productId, Review content);
    
	ReviewResponse getAllReviews(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    String deleteReview(String email, Long productId);

    ReviewResponse getReviewByUser(String email, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ReviewResponse getReviewByProduct(Long productId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);


}

