package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public void saveLogin(AdminForm adminForm) {
		Admin login = new Admin();

		login.setLastName(adminForm.getLastName());
		login.setFirstName(adminForm.getFirstName());
		login.setEmail(adminForm.getEmail());
		login.setPassword(adminForm.getPassword());


		adminRepository.save(login);
	}
}
