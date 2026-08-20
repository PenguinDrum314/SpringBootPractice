package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // このクラスが設定クラスであることを示します
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		// NOTE: HttpSecurityを使用してセキュリティ設定を開始します
		http
				// NOTE: URLごとにアクセス許可や認証の必要性を設定します
				.authorizeHttpRequests(requests -> requests
						// NOTE: ログインしていなくてもアクセスできるURLを指定します
						.requestMatchers(
								"/admin/signin",
								"/admin/signup")
						.permitAll()
						// NOTE: 上で指定したURL以外はログイン必須にします
						.anyRequest().authenticated())
				// NOTE: フォームログインについて設定します
				.formLogin(form -> form
						// NOTE: 自作したログイン画面のURLを指定します
						.loginPage("/admin/signin")
						// NOTE: ログイン情報のPOST送信先を指定します
						.loginProcessingUrl("/admin/signin")
						// NOTE: ログインIDの入力欄のname属性をemailに指定します
						.usernameParameter("email")
						// NOTE: パスワード入力欄のname属性をpasswordに指定します
						.passwordParameter("password")
						// NOTE: ログイン画面へのアクセスを全員に許可します
						.defaultSuccessUrl("/admin/contacts", true)
						// NOTE: ログイン失敗後はログイン画面へ戻します
						.failureUrl("/admin/signin?error")
						.permitAll())
				// NOTE: ログアウト処理を全員に許可します
				.logout(LogoutConfigurer::permitAll);
		// NOTE: 設定内容からSecurityFilterChainを作成して返します
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		// NOTE: BCrypt方式でパスワードをハッシュ化します
		return new BCryptPasswordEncoder();
	}
}