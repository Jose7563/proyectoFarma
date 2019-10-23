package com.farma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.farma.model.entity.User;
import com.farma.service.UserService;

@Controller
@SessionAttributes("user")
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService; 
	 @GetMapping("/ver/{id}")
	 public String ver(@PathVariable Long id, Model model) {
		 User user= userService.getOneById(id);
		 if(user == null) {
			 return "redirect:/users";
		 }
		 model.addAttribute("user", user);
		 model.addAttribute("titulo", "Detalle de cliente " + user.getName());
		 return "users/ver";
	 }
	@GetMapping
	public String list(Model model) {
		model.addAttribute("titulo", "Mantenimiento de Ususario");
		model.addAttribute("users" , userService.getAll());
		
		return "users/list";
	}
	
	@GetMapping("/new")
    public String newUserForm(Model model) {
		User user = new User();
		
        model.addAttribute("user", user);
        return "users/new";
    }
	
	@PostMapping("/save")
    public String saveNewUser(@Validated User user ,BindingResult result,SessionStatus status) {
		
		if(result.hasErrors()) {
			return "users/new";
		}
		
        long id = userService.create(user);
        status.setComplete();
        
        return "redirect:/users";
    }
	@GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.getOneById(id);  
        System.out.println(user.getName());
        model.addAttribute("user", user);
        return "users/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
		userService.update(id, user);
        return "redirect:/users";    
    }
	
	@GetMapping("/delete/{id}")
	private String deleteUser(@PathVariable("id") long id, User user) {
		if(id>0) {
			userService.delete(id);
		}
		  return "redirect:/users";
	}
	
	

}
