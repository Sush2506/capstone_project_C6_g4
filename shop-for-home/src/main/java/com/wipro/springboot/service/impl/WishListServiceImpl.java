package com.wipro.springboot.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wipro.springboot.entity.User;
import com.wipro.springboot.entity.WishList;
import com.wipro.springboot.repository.IWishListRepository;
import com.wipro.springboot.repository.WishListCustomRepository;
import com.wipro.springboot.service.IWishListService;

@Service
@Transactional
public class WishListServiceImpl implements IWishListService {

	private final IWishListRepository wishListRepository;

	@Autowired
	private WishListCustomRepository wishListCustomRepository;

	public WishListServiceImpl(IWishListRepository wishListRepository) {
		this.wishListRepository = wishListRepository;
	}

	@Override
	public WishList createWishlist(WishList wishList) {
		return wishListRepository.save(wishList);
	}

	@Override
	public Boolean deleteWishlist(User user, String productId) {
		return wishListCustomRepository.deleteWishlist(user, productId);
	}

	@Override
	public Page<WishList> findByBuyerEmail(Long id, PageRequest request) {
		return wishListRepository.findAllByUserId(id, request);
	}
}
