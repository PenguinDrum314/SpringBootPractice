package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Contact;
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
    
    @GetMapping("/admin/contacts/:id")
    public String contactDetail(Model model) {
        List<Contact> ContactDetail = contactService.getContactDetail();
        model.addAttribute("contactDetail", ContactDetail);
        System.out.println(ContactDetail);
        return "contactDetail";
    }

}