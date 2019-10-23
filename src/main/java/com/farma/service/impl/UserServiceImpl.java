package com.farma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farma.model.entity.User;
import com.farma.model.repository.UserRepository;
import com.farma.service.UserService;
@Service
public class UserServiceImpl  implements  UserService{
	
	@Autowired
	private UserRepository userRespository;

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return  userRespository.findAll();
	}

	@Override
	public User getOneById(Long id) {
		return userRespository.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not Found!"));
	}

	@Override
	public Long create(User entity) {
		// TODO Auto-generated method stub
		userRespository.save(entity);
		
		return entity.getId();
	}

	@Override
	public void update(Long id, User entity) {
		
		User u = getOneById(id); 
		u.setLastName(entity.getLastName());
		u.setName(entity.getName());
		u.setCreatAt(entity.getCreatAt());
		u.setTickets(entity.getTickets());
		userRespository.save(u); 
		
		
	}

	@Override
	public void delete(Long id) {
		userRespository.deleteById(id);
	}
	

}
