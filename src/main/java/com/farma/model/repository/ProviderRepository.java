package com.farma.model.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.farma.model.entity.Provider;

@Repository
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {

}
