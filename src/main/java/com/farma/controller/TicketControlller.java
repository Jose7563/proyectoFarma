package com.farma.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farma.model.entity.Employee;
import com.farma.model.entity.ItemTicket;
import com.farma.model.entity.Product;
import com.farma.model.entity.Ticket;
import com.farma.service.EmployeeService;
import com.farma.service.ProductService;

@Controller
@RequestMapping("/tickets")
@SessionAttributes("tickets")
public class TicketControlller {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;

	@GetMapping("/form/{employeeId}")
	public String newTickectForm(@PathVariable(value = "employeeId") Long employeeId, Model model,
			RedirectAttributes flash) {

		Employee employee = employeeService.getOneById(employeeId);
		List<Product> listProducts = productService.getAll();
		
		
		if (employee == null) {
			flash.addAttribute("error", "El cliente no exite en la base de datos");
			return "redirect:/employees";
		}
		Ticket ticket = new Ticket();
		ticket.setEmployee(employee);
		model.addAttribute("tickets", ticket);
		model.addAttribute("titulo", "Crear TICKET");
		model.addAttribute("listaProducts", listProducts);
		
		List<ItemTicket> listItem = new ArrayList<ItemTicket>();
		
		ItemTicket listItem1 = new ItemTicket();
		Product p1 = new Product();
		p1.setName("Laptop");
		p1.setUnitPrice(100.0);
		listItem1.setQuantity(4);
		listItem1.setProduct(p1);
		listItem.add(listItem1);
		
		ItemTicket listItem2 = new ItemTicket();
		Product p2 = new Product();
		p2.setName("cpu");
		p2.setUnitPrice(50.0);
		listItem1.setQuantity(6);
		listItem1.setProduct(p2);
		listItem.add(listItem2);
		
		model.addAttribute("listItem", listItem);
		return "tickets/new";
	}

	@PostMapping("/save")
	public String saveNewTicket(@Validated Ticket ticket, BindingResult result) {

		if (result.hasErrors()) {
			return "tickets/form";
		}
//        long id = providerService.create(provider);

		return "redirect:/providers";
	}
}
