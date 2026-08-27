package com.bhalchandra.studentregistrationmvc.config;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bhalchandra.studentregistrationmvc.model.Users;

import com.bhalchandra.studentregistrationmvc.repo.UserRepo;

import com.bhalchandra.studentregistrationmvc.security.JwtAuthenticationFilter;

import com.bhalchandra.studentregistrationmvc.security.RestAccessDeniedHandler;

import com.bhalchandra.studentregistrationmvc.security.RestAuthenticationEntryPoint;

import org.springframework.security.core.userdetails.User;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final RestAuthenticationEntryPoint authenticationEntryPoint;

	private final RestAccessDeniedHandler accessDeniedHandler;


	public SecurityConfig(
	        JwtAuthenticationFilter jwtAuthenticationFilter,
	        RestAuthenticationEntryPoint authenticationEntryPoint,
	        RestAccessDeniedHandler accessDeniedHandler) {

	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;

	    this.authenticationEntryPoint = authenticationEntryPoint;

	    this.accessDeniedHandler = accessDeniedHandler;

	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
	        throws Exception {

		http

		.csrf(csrf -> csrf.disable())


		.authorizeHttpRequests(auth -> auth

				.requestMatchers("/", "/index.jsp", "/login", "/login.jsp")

				.permitAll()

				// index.jsp is directly under webapp (learning project)

				.requestMatchers("/auth/login")

			.permitAll()			    

		    .requestMatchers("/deleteStudent")

		    .hasRole("ADMIN")


		    .requestMatchers("/showStudents")

		    .hasAnyRole("ADMIN","FACULTY")


		    .requestMatchers("/updateStudent")

		    .hasAnyRole("ADMIN","FACULTY")


		    //REst control

		    .requestMatchers(HttpMethod.GET, "/student", "/student/**")
		    .hasAnyRole("ADMIN", "FACULTY")

		    .requestMatchers(HttpMethod.POST, "/student")
		    .hasAnyRole("ADMIN", "FACULTY")



		    .requestMatchers(HttpMethod.DELETE, "/student/**")

	    .hasRole("ADMIN")


		    .anyRequest()

		    .authenticated()

		)


		.exceptionHandling(exception -> exception

				.defaultAuthenticationEntryPointFor(
					    authenticationEntryPoint,
					    request -> request.getRequestURI().startsWith("/api/")
					)
					.defaultAccessDeniedHandlerFor(
					    accessDeniedHandler,
					    request -> request.getRequestURI().startsWith("/api/")
					)		)

//MVC Frontend
		.formLogin(form -> form

			 .loginPage("/login")

			    .loginProcessingUrl("/login")
			    
			    .failureUrl("/login?error=true")

			    .permitAll()

			    )


		.logout(logout -> logout

		    .logoutSuccessUrl("/")

		    .permitAll()

		)


		.addFilterBefore(

		        jwtAuthenticationFilter,

		        UsernamePasswordAuthenticationFilter.class

		)

		;


		return http.build();

	}


	@Bean
	public PasswordEncoder passwordEncoder() {

	    return new BCryptPasswordEncoder();

	}


	@Bean
	public AuthenticationManager authenticationManager(

            AuthenticationConfiguration config)

            throws Exception {

        return config.getAuthenticationManager();

    }



	@Bean
	CommandLineRunner init(UserRepo repo,

	                       PasswordEncoder encoder) {

	    return args -> {

	    	if(repo.findByUsername("admin").isEmpty()) {

	        Users user = new Users();

	        user.setUsername("admin");

	        user.setPassword(encoder.encode("admin123"));

	        user.setRole("ADMIN");

	        repo.save(user);

	    	}


	    	if (repo.findByUsername("faculty").isEmpty()) {

	            Users faculty = new Users();

	            faculty.setUsername("faculty");

	            faculty.setPassword(encoder.encode("faculty123"));

	            faculty.setRole("FACULTY");

	            repo.save(faculty);

	        }


	    	 if (repo.findByUsername("student").isEmpty()) {

	             Users student = new Users();

	             student.setUsername("student");

	             student.setPassword(encoder.encode("student123"));

	             student.setRole("STUDENT");

	             repo.save(student);

	         }

	    };

	}

}




//@##########################################

/*

 * @Bean

	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

	    UserDetails admin = User

	            .withUsername("admin")

	            .password(passwordEncoder.encode("admin123"))

	            .roles("ADMIN")

	            .build();

	    UserDetails faculty = User

	            .withUsername("faculty")

	            .password(passwordEncoder.encode("faculty123"))

	            .roles("FACULTY")

	            .build();

	    UserDetails student = User

	            .withUsername("student")

	            .password(passwordEncoder.encode("student123"))

	            .roles("STUDENT")

	            .build();



	    return new InMemoryUserDetailsManager(admin,faculty,student);

	}

	*/