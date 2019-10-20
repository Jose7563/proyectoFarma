package com.farma.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farma.model.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
