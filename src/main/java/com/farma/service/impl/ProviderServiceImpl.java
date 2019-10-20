package com.farma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farma.model.entity.Provider;
import com.farma.model.repository.ProviderRepository;
import com.farma.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ProviderRepository providerRepository; 
	
	@Override
	public List<Provider> getAll() {
		
		return providerRepository.findAll();
	}

	@Override
	public Provider getOneById(Long id) {
		return providerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Provider Not Found!"));
		
	}

	@Override
	public Long create(Provider entity) {
		providerRepository.save(entity);
		return entity.getId() ;
	}

	@Override
	public void update(Long id, Provider entity) {
		Provider p=getOneById(id);
		p.setEmail(entity.getEmail());
		p.setName(entity.getName());
		p.setPhone(entity.getPhone());
		p.setRuc(entity.getRuc());
		p.setProducts(entity.getProducts());
		providerRepository.save(p);
		
	}

	@Override
	public void delete(Long id) {
		providerRepository.deleteById(id);
	}

}
