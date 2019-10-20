package com.farma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farma.model.entity.Provider;
import com.farma.service.ProviderService;

@Controller
@RequestMapping("providers")
public class ProviderController {
	@Autowired
	private ProviderService providerService; 
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("titulo", "Mantenimiento de proveedor");
		model.addAttribute("providers" , providerService.getAll());
		
		return "categories/list";
	}
	
	@GetMapping("/new")
    public String newProviderForm(Model model) {
		Provider provider = new Provider();
		
        model.addAttribute("category", provider);
        return "providers/new";
    }
	
	@PostMapping("/save")
    public String saveNewProvider(Provider provider) {
        long id = providerService.create(provider);
        return "redirect:/provider";
    }
	@GetMapping("/edit/{id}")
    public String editProviderForm(@PathVariable("id") long id, Model model) {
        Provider provider = providerService.getOneById(id);  
        System.out.println(provider.getName());
        model.addAttribute("category", provider);
        return "providers/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateProvider(@PathVariable("id") long id, Provider provider) {
		providerService.update(id, provider);
        return "redirect:/providers";    
    }
	
	

}
