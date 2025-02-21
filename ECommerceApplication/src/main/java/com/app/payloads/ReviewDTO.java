package com.app.payloads;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private UserDTO user;
    private ProductDTO product;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
