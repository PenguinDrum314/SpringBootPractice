package com.example.demo.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {
	void saveContact(ContactForm contactForm);
	List<Contact> getContactList();
	@Nullable
	Object getContactDetail(long id);
	@Nullable
	Object getContactEdit(long id);

}
