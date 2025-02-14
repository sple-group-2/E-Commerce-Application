package com.app.entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;

	@NotBlank
	@Column(unique = true) 
	private String couponName;

    @NotBlank
	@Column(unique = true)
    private String couponCode;

    private double discountValue;

	@OneToMany(mappedBy = "coupon", cascade =  { CascadeType.PERSIST, CascadeType.MERGE } )
	private List<Product> products = new ArrayList<>();
}
