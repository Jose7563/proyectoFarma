package com.farma.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farma.model.entity.Category;
import com.farma.model.repository.CategoryRepository;
import com.farma.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getOneById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoy Not Found!"));
	
	}

	@Override
	public Long create(Category entity) {
		 categoryRepository.save(entity);
		 return entity.getId();
		
	}

	@Override
	public void update(Long id, Category entity) {
		Category category = getOneById(id);
		category.setDescription(entity.getDescription());
		category.setName(entity.getName());
		categoryRepository.save(category);
	}

	@Override
	public void delete(Long id) {
		categoryRepository.deleteById(id);
		
	} 
	
	
	

}
