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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farma.common.PageRender;
import com.farma.model.entity.Employee;
import com.farma.service.EmployeeService;

@Controller
@SessionAttributes("employee")
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService userService; 
	
	@GetMapping("/view/{id}")
	 public String ver(@PathVariable Long id, Model model) {
		 Employee employee= userService.getOneById(id);
		 if(employee == null) {
			 return "redirect:/employees";
		 }
		 model.addAttribute("employee", employee);
		 model.addAttribute("titulo", "Detalle de empleado " + employee.getName());
		 return "employees/view";
	 }
	@GetMapping
	public String list(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page <Employee> employees= userService.findAll(pageRequest);
		PageRender<Employee> pageRender= new PageRender<>("/employees", employees);
		
		model.addAttribute("titulo", "Mantenimiento de Empleado");
		model.addAttribute("employees" , employees);
		model.addAttribute("page", pageRender);
		
		return "employees/list";
	}
	
	@GetMapping("/new")
    public String newUserForm(Model model) {
		Employee employee = new Employee();
		
        model.addAttribute("employee", employee);
        return "employees/new";
    }
	
	@PostMapping("/save")
    public String saveNewUser(@Validated Employee employee ,BindingResult result,RedirectAttributes flash,SessionStatus status) {
		
		if(result.hasErrors()) {
			return "employees/new";
		}
		
        long id = userService.create(employee);
        status.setComplete();
        flash.addFlashAttribute("success", "Empleado creado con exito");
        
        return "redirect:/employees";
    }
	@GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        Employee employee = userService.getOneById(id);  
        System.out.println(employee.getName());
        model.addAttribute("employee", employee);
        return "employees/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Employee employee, RedirectAttributes flash) {
		userService.update(id, employee);
		flash.addFlashAttribute("success", "Empleado actualizado con exito"); 
		
        return "redirect:/employees";    
    }
	
	@GetMapping("/delete/{id}")
	private String deleteUser(@PathVariable("id") long id, Employee employee, RedirectAttributes flash) {
		if(id>0) {
			userService.delete(id);
			flash.addFlashAttribute("success", "Empleado eliminado con exito");
		}
		  return "redirect:/employees";
	}
	
	

}
