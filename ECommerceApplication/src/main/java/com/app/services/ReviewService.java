package com.app.services;

import com.app.entites.Review;
import com.app.payloads.ReviewDTO;

public interface ReviewService {

    ReviewDTO createReview(String email, Long productId, Review content);

    ReviewDTO updateReview(String email, Long productId, Review content);
    
	// BrandResponse getBrands(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    String deleteReview(String email, Long productId);

}

