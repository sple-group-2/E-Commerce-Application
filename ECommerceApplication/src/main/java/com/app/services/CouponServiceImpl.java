package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Coupon;
import com.app.payloads.AddressDTO;
import com.app.payloads.CouponDTO;
import com.app.repositories.CouponRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    private CouponRepo couponRepo;

    @Autowired
	private ModelMapper modelMapper;

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

    @Override
    public List<CouponDTO> getAllCoupons(){
        List<Coupon> coupons = couponRepo.findAll();

        List<CouponDTO> couponDTOs = coupons.stream().map(coupon -> modelMapper.map(coupon, CouponDTO.class))
				.collect(Collectors.toList());
        return couponDTOs;
    }

    @Override
    public CouponDTO getCouponByName(String couponName){
        Coupon coupon = couponRepo.findByCouponName(couponName);

        return modelMapper.map(coupon, CouponDTO.class);
    }

    @Override
    public CouponDTO deleteCoupon(String couponName){
        Coupon coupon = couponRepo.findByCouponName(couponName);

        couponRepo.delete(coupon);
        return modelMapper.map(coupon, CouponDTO.class);
    }
}

