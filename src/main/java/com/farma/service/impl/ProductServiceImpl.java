package com.farma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farma.model.entity.Product;
import com.farma.model.repository.ProductRepository;
import com.farma.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository; 

	@Override
	public List<Product> getAll() {

		return productRepository.findAll() ;
	}

	@Override
	public Product getOneById(Long id) {
		
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product Not Found!"));
	}

	@Override
	public Long create(Product entity) {
		productRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, Product entity) {
		
		Product p = getOneById(id);
		p.setCategory(entity.getCategory());
		p.setExpirationDate(entity.getExpirationDate());
		p.setName(entity.getName());
		p.setOrders(entity.getOrders());
		p.setProviders(entity.getProviders());
		p.setQuantity(entity.getQuantity());
		p.setUbication(entity.getUbication());
		productRepository.save(p);
		
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
		
	}

}
