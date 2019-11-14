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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farma.model.entity.Employee;
import com.farma.model.entity.Ticket;
import com.farma.service.EmployeeService;

@Controller
@RequestMapping("/tickets")
@SessionAttributes("tickets")
public class TicketControlller {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/form/{employeeId}")
    public String newTickectForm(@PathVariable(value="employeeId") Long employeeId,
    		Model model,RedirectAttributes flash) {
		
		Employee employee= employeeService.getOneById(employeeId);
		if(employee == null) {
			flash.addAttribute("error", "El cliente no exite en la base de datos");
			return "redirect:/employees";	
		}
		Ticket ticket= new Ticket(); 
		ticket.setEmployee(employee);
     model.addAttribute("tickets", ticket);
     model.addAttribute("titulo", "Crear TICKET");
        return "tickets/new";
    }
	@PostMapping("/save")
    public String saveNewTicket(@Validated Ticket ticket ,  BindingResult result) {
		
		if(result.hasErrors()) {
			return "tickets/form";
		}
//        long id = providerService.create(provider);
     
        return "redirect:/providers";
    }
}
