package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.ReviewDTO;
import com.app.services.ReviewService;
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

	// @GetMapping("/admin/orders")
	// public ResponseEntity<OrderResponse> getAllOrders(
	// 		@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
	// 		@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
	// 		@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_ORDERS_BY, required = false) String sortBy,
	// 		@RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
		
	// 	OrderResponse orderResponse = orderService.getAllOrders(pageNumber, pageSize, sortBy, sortOrder);

	// 	return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.FOUND);
	// }
	
	// @GetMapping("public/users/{email}/orders")
	// public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable String email) {
	// 	List<OrderDTO> orders = orderService.getOrdersByUser(email);
		
	// 	return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.FOUND);
	// }
	
	// @GetMapping("public/users/{email}/orders/{orderId}")
	// public ResponseEntity<OrderDTO> getOrderByUser(@PathVariable String email, @PathVariable Long orderId) {
	// 	OrderDTO order = orderService.getOrder(email, orderId);
		
	// 	return new ResponseEntity<OrderDTO>(order, HttpStatus.FOUND);
	// }
	
}
