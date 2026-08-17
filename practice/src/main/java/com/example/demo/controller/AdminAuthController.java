package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

public class AdminAuthController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/signup")
	public String SignUp(Model model) {
		 model.addAttribute("adminForm", new AdminForm());
		return "signup";
	}
	
	@PostMapping("/admin/signup")
	public String signup(
	        @Validated @ModelAttribute("adminForm") AdminForm adminForm,
	        BindingResult errorResult) {

	    }

	    return "redirect:/admin/signup/complete";
	}

}
