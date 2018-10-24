package com.example.demo.securite;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    /*auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
        .withUser("admin")
        .password("1234")
        .roles("ADMIN","USER")
        .and()
        .withUser("user")
        .password("1234")
        .roles("USER");*/
		
		auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		    .dataSource(datasource)
		    .usersByUsernameQuery("select username as principal,password as credentials , active "
		    		+ " from users where username=? ")
		    .authoritiesByUsernameQuery("select username as principal,role as role from users_roles "
		    		+ "where username=? ")
		    .rolePrefix("ROLE_");
		   
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
      http.formLogin().loginPage("/login");
      http.authorizeRequests()
       .antMatchers("/operations","/consulterCompte")
       .hasRole("USER");
      http.authorizeRequests().antMatchers("/addVersement").hasRole("ADMIN");
	}
	
	
}
