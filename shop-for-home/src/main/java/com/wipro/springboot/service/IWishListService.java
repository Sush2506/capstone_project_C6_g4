package com.wipro.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.wipro.springboot.entity.User;
import com.wipro.springboot.entity.WishList;

public interface IWishListService {

	Boolean deleteWishlist(User user, String productId);

	Page<WishList> findByBuyerEmail(Long id, PageRequest request);

	WishList createWishlist(WishList wishList);

}
