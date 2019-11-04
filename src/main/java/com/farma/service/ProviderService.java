package com.farma.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.farma.model.entity.Provider;

public interface ProviderService extends CrudService<Provider, Long> {
	public Page<Provider> findAll(Pageable pageable);
}
