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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farma.model.entity.Category;
import com.farma.model.entity.Employee;
import com.farma.service.CategoryService;

@Controller
@SessionAttributes("categories")
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService; 
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("titulo", "Mantenimiento de Catgorias");
		model.addAttribute("categories", categoryService.getAll());
		
		return "categories/list";
	}
	
	@GetMapping("/new")
    public String newCategoryForm(Model model) {
		Category category = new Category();
		
        model.addAttribute("category", category);
        return "categories/new";
    }
	
	@PostMapping("/save")
    public String saveNewCategory(@Validated Category category, BindingResult result, SessionStatus status ,RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "categories/new";
		}
		
        long id = categoryService.create(category);
        status.setComplete();
        flash.addFlashAttribute("success", "La categoria se ha creado con exito"); 
        
        return "redirect:/categories";
    }
	
	@GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") long id, Model model) {
        Category category = categoryService.getOneById(id);
        System.out.println(category.getName());
        model.addAttribute("category", category);
        return "categories/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") long id, Category category,RedirectAttributes flash) {
        categoryService.update(id, category);
        flash.addFlashAttribute("success", "La categoria actualizada con exito");

        return "redirect:/categories";    
    }
	
	@GetMapping("/delete/{id}")
	private String deleteUser(@PathVariable("id") long id, Employee user
			, RedirectAttributes flash) {
		if(id>0) {
			categoryService.delete(id);
			flash.addFlashAttribute("success", "La categoria se elimino con exito");
		}
		
		  return "redirect:/categories";
	}
}
