package com.xpto.cities.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServiceConfiguration extends ResourceServerConfigurerAdapter {

	  @Override
	  public void configure(final HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeRequests()
	    .antMatchers("/auth/**", "/api/usuario/cadastrar", "/v2/api-docs", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**")
		.permitAll().anyRequest().authenticated()
	    .antMatchers("/**").hasAuthority("ADMIN");
	    // @formatter:on
	  }
	}
