package hu.cubix.logistics.bencepar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hu.cubix.logistics.bencepar.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails addressUser = User.withDefaultPasswordEncoder()
	                .username("addressmgr")
	                .password("password")
	                .authorities("AddressManager")
	                .build();
	                
	        UserDetails transportUser = User.withDefaultPasswordEncoder()
	                .username("transportmgr")
	                .password("password")
	                .authorities("TransportManager")
	                .build();

	        return new InMemoryUserDetailsManager(addressUser, transportUser);
	    }

	    @Bean
	    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtFilter) throws Exception {
	        http.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authz -> authz
	                .requestMatchers("/api/login").permitAll()
	                .anyRequest().authenticated()
	            )
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
}
