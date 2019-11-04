package com.farma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farma.common.PageRender;
import com.farma.model.entity.Provider;
import com.farma.service.ProviderService;

@Controller
@SessionAttributes("providers")
@RequestMapping("/providers")
public class ProviderController {
	@Autowired
	private ProviderService providerService; 
	
	@GetMapping
	public String list(@RequestParam(name="page", defaultValue="0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		Page <Provider> providers= providerService.findAll(pageRequest);
		PageRender<Provider> pageRender= new PageRender<>("/providers", providers);
		
		model.addAttribute("titulo", "Mantenimiento de proveedor");
		model.addAttribute("providers" ,providers );
		model.addAttribute("page", pageRender);
		
		return "providers/list";
	}
	
	@GetMapping("/new")
    public String newProviderForm(Model model) {
		Provider provider = new Provider();
		
        model.addAttribute("provider", provider);
        return "providers/new";
    }
	
	@PostMapping("/save")
    public String saveNewProvider(@Validated Provider provider, BindingResult result,RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return "providers/new";
		}
        long id = providerService.create(provider);
        flash.addFlashAttribute("success", "El proveedor se guardo con exito");
        return "redirect:/providers";
    }
	@GetMapping("/edit/{id}")
    public String editProviderForm(@PathVariable("id") long id, Model model) {
        Provider provider = providerService.getOneById(id);  
        System.out.println(provider.getName());
        model.addAttribute("provider", provider);
        return "providers/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateProvider(@PathVariable("id") long id, Provider provider,RedirectAttributes flash) {
		providerService.update(id, provider);
		flash.addFlashAttribute("sucess", "El proveedor se actualizo con exito"); 
        return "redirect:/providers";    
    }
	@GetMapping("/delete/{id}")
	private String deleteProviders(@PathVariable("id") long id,Provider provider,RedirectAttributes flash) {
		if(id>0) {
			providerService.delete(id);
			flash.addFlashAttribute("sucess","El proveedor se elimino con exito"); 
		}
		  return "redirect:/providers";
	}
	

}
