package com.anonyblah.xxalimi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.anonyblah.xxalimi.service.LoginService;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	private static final String REMEMBER_ME_KEY = "rememberMe";

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	LoginService loginService;
	
	
	 
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/font-awesome/**", "/stomp/**", "/webjars/**", "/css/**", "/js/**", "/img/**"); // /webjars/나 /css와  같은 정적 리소스에 접근시 시큐리터 설정 무시
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(loginService.passwordEncoding());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		System.out.println(
				"Security------------------------------------------------------------------------------------");
		
		http.authorizeRequests()
				.antMatchers("/home", "/add/searchPage", "/mindmap").hasRole("USER") 	// 해당 경로 접근시  "USER"권한이 아니면 로그인 페이지로 자동 이동
//				.antMatchers("/**").permitAll() 								// 모든 사용자가 접근 가능
				.antMatchers("/auth/**", "/register/**").anonymous(); 		// "/auth/**"경로는 인증된 사용자가 접근 불가능
//				.anyRequest().authenticated(); 				// 이 외에 나머지 경로는 권한이 없으면 접근 불가

		http.formLogin()
				.loginPage("/login").permitAll()			// 사용자가 지정한 로그인 페이지 경로
				.loginProcessingUrl("/login") 			// session에 인증정보 저장. 로그인을 처리하는 url
				.defaultSuccessUrl("/login", true); 		// 인증 성공 후 이동할 경로
//				.failureUrl("/auth/fail") 				// 로그인 실패시 출력될 경로
//				.usernameParameter("username")				// 사용자 계정명을 어떤 파라미터명으로 받을지 설정. form안에 input태그의 계정명과 동일하게 설정
//				.passwordParameter("password")				

		http.logout()
				.logoutUrl("/logout")
//				.invalidateHttpSession(true)				//	세션 삭제할 건지 설정
				.logoutSuccessUrl("/login"); 				// 로그아웃을 마친 뒤에 보여지는 화면 경로 지정
//				.deleteCookies("remember-me", "JSESSIONID"); // 로그아웃 시 지울 쿠키 및 세션를 지정하는 속성
				// .permitAll();
				
		http.rememberMe()
				.key(REMEMBER_ME_KEY)
				.rememberMeServices(persistentTokenRememberMeServices());
//				.rememberMeParameter("remember-me");
//				.rememberMeCookieName("my-remember-me")
//				.tokenRepository(persistentTokenRepository())
//				.tokenValiditySeconds(86400);				// 세션 유지할 시간 설정
				// .tokenRepository(PersistentTokenRepository());

		http.sessionManagement()
				.maximumSessions(2)					// 중목 세션 최대 2개
				.maxSessionsPreventsLogin(true)
				.sessionRegistry(sessionRegistry());

		http.csrf().disable();
	}


	
	@Bean
	public PersistentTokenBasedRememberMeServices persistentTokenRememberMeServices() {
		PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices = 
				new PersistentTokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService, jdbcTokenRepository());
		
		return persistentTokenBasedRememberMeServices;
	}  
	@Bean
	public JdbcTokenRepositoryImpl jdbcTokenRepository(){
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setCreateTableOnStartup(false);
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}
	
	// Register HttpSessionEventPublisher
	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
	}


}
