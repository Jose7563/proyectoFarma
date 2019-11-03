package com.farma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farma.model.entity.Category;
import com.farma.model.entity.Product;
import com.farma.model.entity.Provider;
import com.farma.model.entity.User;
import com.farma.service.CategoryService;
import com.farma.service.ProductService;
import com.farma.service.ProviderService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private CategoryService categoryService; 
	@Autowired
	private ProviderService providerService; 
	@Autowired
	private ProductService productService; 

	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("titulo", "Mantenimiento de Productos");
		model.addAttribute("products",productService.getAll() );
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("providers", providerService.getAll());

		
		return "products/list";
	}
	
	
	@GetMapping("/new")
    public String newProductsForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		 List<Category> categories = categoryService.getAll();
		 List<Provider> providers = providerService.getAll();
		 model.addAttribute("providers", providers);
		 model.addAttribute("categories", categories);


        
        return "products/new";
    }
	
	@PostMapping("/save")
    public String saveNewProduct(@Validated Product product,BindingResult result, SessionStatus status ,RedirectAttributes flash) {
        
		if(result.hasErrors()) {
			return "products/new";
		}
		long id = productService.create(product);
		status.setComplete();
		flash.addFlashAttribute("success", "El producto se ha creado con exito");
        
        
        return "redirect:/products";
    }
	@GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") long id, Model model) {
        Product product = productService.getOneById(id);
        List<Category> categories = categoryService.getAll();
        List<Provider> providers = providerService.getAll();
        model.addAttribute("categories",categories);
        model.addAttribute("providers",providers);        
        model.addAttribute("product", product);
        return "products/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, Product product) {
        productService.update(id, product);
        return "redirect:/products";    
    }
	
	
	@GetMapping("/delete/{id}")
	private String deleteProduct(@PathVariable("id") long id, Product product
			, RedirectAttributes flash) {
		if(id>0) {
			productService.delete(id);
			flash.addFlashAttribute("success", "El producto se elimino con exito");
		}
		
		  return "redirect:/products";
	}
}
