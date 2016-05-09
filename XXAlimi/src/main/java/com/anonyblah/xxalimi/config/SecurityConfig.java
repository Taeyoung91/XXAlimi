package com.anonyblah.xxalimi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.anonyblah.xxalimi.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	LoginService loginService;
	
	 
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/stomp/**", "/webjars/**", "/css/**", "/js/**", "/img/**"); // /webjars/나 /css와  같은 정적 리소스에 접근시 시큐리터 설정 무시
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		System.out.println(
				"Security------------------------------------------------------------------------------------");
		http
			.authorizeRequests()
				.antMatchers("/user/**").hasRole("USER") 	// "/user/**"경로는 "USER"권한이 있어야 접근 가능
//				.antMatchers("/**").hasRole("ADMIN")
				// .permitAll() // 모든 사용자가 접근 가능
				.antMatchers("/auth/**", "/register/**").anonymous() 		// "/auth/**"경로는 인증된 사용자가 접근 불가능
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated() 				// 이 외에 나머지 경로는 권한이 없으면 접근 불가
				.and()
			.formLogin()
				.loginPage("/login") 						// 사용자가 지정한 로그인 페이지 경로
				.loginProcessingUrl("/user/home") 			// session에 인증정보 저장
				.defaultSuccessUrl("/user/home", true) 		// 인증 성공 후 이동할 경로
				// .failureUrl("/auth/fail") 				// 로그인 실패시 출력될 경로
				 .usernameParameter("username")
				 .passwordParameter("password")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login") 				// 로그아웃을 마친 뒤에 보여지는 화면 경로 지정
				.deleteCookies(/* "rememberMe", */"JSESSIONID") // 로그아웃 시 지워야 할 쿠키를 지정하는 속성
				// .permitAll();
				.and()
			.rememberMe()
				.key("key")
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(43200);
				// .tokenRepository(PersistentTokenRepository());
		http.csrf().disable();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("con");
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(loginService.passwordEncoding());
//			.usersByUsernameQuery("sql...")
//			.authoritiesByUsernameQuery("sql...");
//			.usersByUsernameQuery("select username, password from users where username=?")
//			.authoritiesByUsernameQuery("select user_email, role from user_roles where user_email=?");
//		auth.inMemoryAuthentication()
//			.withUser("user").password("1234").roles("USER")
//			.and()
//			.withUser("admin").password("1234").roles("ADMIN", "USER");
//		auth.userDetailsService(loginService);	Dao와 inMemory 둘다 사용될때
	}

}
