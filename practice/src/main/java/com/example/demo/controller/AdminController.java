package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/admin/contacts")
	public String contactList(Model model) {
		List<Contact> contactList = contactService.getContactList();
		model.addAttribute("contactList", contactList);
		return "contactList";
	}

	@GetMapping("/admin/contacts/{id}")
	public String contactDetail(@PathVariable long id, Model model) {
		model.addAttribute("contact", (contactService.getContactDetail(id)));

		return "contactDetail";
	}

	@GetMapping("/admin/contacts/{id}/edit")
	public String contactEdit(@PathVariable long id, Model model) {
		model.addAttribute("contact", (contactService.getContactEdit(id)));

		return "contactEdit";
	}

	@PutMapping("/admin/contacts/{id}/edit")
	public String contact(@Validated @ModelAttribute("contact") @PathVariable long id, ContactForm contactForm,
			BindingResult errorResult,
			Model model) {

		if (errorResult.hasErrors()) {
			return "contactEdit";
		}

		contactService.updateContact(id, contactForm);
		return "redirect:/admin/contacts";
	}
	
	@DeleteMapping("/admin/contacts/{id}/delete")
	public String contactDelete(@PathVariable long id) {
		contactService.deleteContact(id);
		System.out.println(id);

		return "redirect:/admin/contacts";
	}


}