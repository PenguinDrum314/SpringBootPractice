package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@Controller
public class AdminAuthController {
	@Autowired
	private AdminService adminService;

	@GetMapping("/admin/signup")
	public String SignUp(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "signup";
	}

	@PostMapping("/admin/signup")
	public String signup(@Validated @ModelAttribute("adminForm") AdminForm adminForm,
			BindingResult errorResult,
			Model model) {
		if (errorResult.hasErrors()) {
			return "signup";
		}
//		NOTE: フォームの入力内容をDBへ保存する処理はServiceの役割になるため、AdminFormをServiceへ渡す
		adminService.saveLogin(adminForm);
		return "redirect:/admin/signin";

	}
//  NOTE:空のAdminFormを用意
	@GetMapping("/admin/signin")
	public String signin(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "signin";
	}
}
