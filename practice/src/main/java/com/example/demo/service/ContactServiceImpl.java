package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();

		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());

		contact.setCreatedAt(LocalDateTime.now());
		contact.setUpdatedAt(LocalDateTime.now());

		contactRepository.save(contact);
	}

	@Override
	public List<Contact> getContactList() {
		return contactRepository.findAll();
	}

	@Override
	public @Nullable Contact getContactDetail(long id) {
		return contactRepository.findById(id).orElse(null);
	}

	@Override
	public @Nullable Contact getContactEdit(long id) {
		return contactRepository.findById(id).orElse(null);
	}

	//	メソッド内のDB更新処理を、ひとまとまりで扱う
	@Transactional
	@Override
	public void updateContact(long id, ContactForm contactForm) {
		contactRepository.findById(id).orElse(null);
		Contact contact = contactRepository.findById(id).orElse(null);

		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());

		contact.setCreatedAt(LocalDateTime.now());
		contact.setUpdatedAt(LocalDateTime.now());

		contactRepository.save(contact);
	}
	
	@Override
	@Transactional
	public void deleteContact(long id) {

		contactRepository.deleteById(id);

	}

}