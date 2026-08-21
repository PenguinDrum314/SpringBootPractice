package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
	@Autowired
	private final AdminRepository adminRepository;
	// NOTE: パスワードをハッシュ化するオブジェクト
	private final PasswordEncoder passwordEncoder;
	
	public AdminServiceImpl(
			AdminRepository adminRepository,
			PasswordEncoder passwordEncoder) {

		this.adminRepository = adminRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void saveLogin(AdminForm adminForm) {
		Admin login = new Admin();

		login.setLastName(adminForm.getLastName());
		login.setFirstName(adminForm.getFirstName());
		login.setEmail(adminForm.getEmail());
		login.setPassword(adminForm.getPassword());
		
		// NOTE: パスワードをBCrypt形式にハッシュ化して設定
		login.setPassword(
				passwordEncoder.encode(
					adminForm.getPassword()
				)
			);

		adminRepository.save(login);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		return adminRepository.findByEmail(email)
				.<UserDetails>map(admin ->
						User.withUsername(admin.getEmail())
								.password(admin.getPassword())
								.roles("ADMIN")
								.build())
				.orElseThrow(() ->
				new UsernameNotFoundException(email));
	}
	
}
