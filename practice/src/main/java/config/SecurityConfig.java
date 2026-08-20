package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // このクラスは設定クラスであることを示します
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		// NOTE: HttpSecurityを使用してセキュリティ設定を開始します
		http
		    // NOTE: URLごとにアクセスを許可するか、認証を必要とするか設定
			.authorizeHttpRequests((requests) -> requests
                //	NOTE:「ログインしていなくてもアクセスできるURL」の指定
				.requestMatchers("/signin", "/signup").permitAll()
				// NOTE: 上で指定したURL以外は、すべてログイン
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
					.loginPage("/signin")
					.permitAll()
				)
			.logout(LogoutConfigurer::permitAll);
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
