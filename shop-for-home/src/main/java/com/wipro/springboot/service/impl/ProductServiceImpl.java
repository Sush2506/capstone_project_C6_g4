package com.wipro.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.springboot.entity.Product;
import com.wipro.springboot.enums.ProductStatusEnum;
import com.wipro.springboot.repository.IProductRepository;
import com.wipro.springboot.service.IProductCategoryService;
import com.wipro.springboot.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Autowired
	IProductCategoryService categoryService;

	@Override
	public Product findOne(String productId) {
		Product product = productRepository.findByProductId(productId);
		return product;
	}

	@Override
	public Page<Product> findUpAll(Pageable pageable) {
		return productRepository.findAllByProductStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(), pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAllByOrderByProductId(pageable);
	}

	@Override
	public Page<Product> findAllInCategory(Integer categoryType, Pageable pageable) {
		return productRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
	}

	@Override
	@Transactional
	public void increaseStock(String productId, int stock) {
		Product product = findOne(productId);

		int update = product.getProductStock() + stock;
		product.setProductStock(update);
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void decreaseStock(String productId, int stock) {
		Product product = findOne(productId);
		int update = product.getProductStock() - stock;

		product.setProductStock(update);
		productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		categoryService.findByCategoryType(product.getCategoryType());
		return productRepository.save(product);
	}

	@Override
	public Product save(Product product) {
		return update(product);
	}

	@Override
	public void delete(String productId) {
		Product product = findOne(productId);
		productRepository.delete(product);

	}

	@Override
	@Transactional
	public List<Product> findAll() {
		return productRepository.findAll();
	}

}
