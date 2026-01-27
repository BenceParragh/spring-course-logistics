package hu.cubix.logistics.bencepar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.cubix.logistics.bencepar.model.LogisticsUser;
import hu.cubix.logistics.bencepar.repository.LogisticsUserRepository;


@Service
public class LogisticsUserDetailsService implements UserDetailsService {

	@Autowired
	LogisticsUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LogisticsUser user = userRepository.findById(username)
			.orElseThrow(() -> new UsernameNotFoundException(username));
		
		return new User(username, user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
	
	}
	
}
