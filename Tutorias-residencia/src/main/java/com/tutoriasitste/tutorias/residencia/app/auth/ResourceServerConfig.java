package com.tutoriasitste.tutorias.residencia.app.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers(HttpMethod.GET,"/api/alumnos/pagina/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/alumnos/pagina/**","/api/usuarios/pagina/**","/api/archivos/pagina/**","/api/docentes/pagina/**").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/usuarios/{id}","/api/alumnos/{id}","/api/archivos/{id}","/api/docentes/{id}","/api/roles/{id}").hasAnyRole("ADMIN","DOCENTE","ALUMNO")
		.antMatchers(HttpMethod.POST, "/api/usuarios/usuario-role/{id}","/api/roles/create/roles").permitAll()
		.antMatchers(HttpMethod.GET,"/api/roles/filtrar/role-nombre/{term}","/api/roles","/api/roles/pagina/**","/api/archivos/uploads/file-pdf/{id}","/api/archivos/uploads/file-excel/{id}","/api/archivos/uploads/file-word/{id}").permitAll()
		/*.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers("/api/clientes/**").hasRole("ADMIN")*/
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	
}
