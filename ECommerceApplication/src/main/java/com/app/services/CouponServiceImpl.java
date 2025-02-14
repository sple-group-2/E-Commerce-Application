package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import com.app.repositories.CouponRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    private CouponRepo couponRepo;

    @Override
    public Coupon createCoupon(CouponDTO couponDTO){
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponDTO.getCouponName());
        coupon.setCouponCode(couponDTO.getCouponCode());
        coupon.setDiscountValue(couponDTO.getDiscountValue());
        couponRepo.save(coupon);
        return coupon;
    }

    @Override
    public Coupon updateCoupon(CouponDTO couponDTO, String couponName){
        Coupon coupon = couponRepo.findByCouponName(couponName);

        if (coupon == null) {
            throw new RuntimeException("Coupon not found with couponName: " + couponName);
        }
        
        coupon.setCouponName(couponDTO.getCouponName());
        coupon.setCouponCode(couponDTO.getCouponCode());
        coupon.setDiscountValue(couponDTO.getDiscountValue());

        System.out.println(coupon);

        return couponRepo.save(coupon);
    }
}

