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
import com.example.demo.form.AdminForm;
import com.example.demo.form.ContactForm;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/admin/contacts")
	public String contactList(Model model) {
//		このListの中にはContact型のデータを入れる。ContactはContact.javaのpublic class Contactのこと
		List<Contact> contactList = contactService.getContactList();
		//		addAttribute HTMLで使えるようにModelへ入れる
		model.addAttribute("contactList", contactList);
		return "contactList";
	}

	// URLのidを受け取り、該当するお問い合わせを取得して詳細画面に渡す
	@GetMapping("/admin/contacts/{id}")
//	 @PathVariable で id = 値 を受け取る
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
	//	@Validated ContactFormに入った値をチェック @ModelAttribute HTMLフォームから送られてきた入力内容を ContactForm に入れる 
	//	@PathVariable URLの {id} を id という変数で受け取る
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

		return "redirect:/admin/contacts";
	}
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/signup")
	public String SignUp(Model model) {
		 model.addAttribute("adminForm", new AdminForm());
		return "signup";
	}
	
	

}