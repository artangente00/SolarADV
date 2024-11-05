package com.PlayGroundAdv.Solar.security;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AuthenticationRepository authenticationRepository;

	public UserDetailsServiceImpl(AuthenticationRepository authenticationRepository) {
		this.authenticationRepository = authenticationRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) {
		final AuthentificationEntity user = authenticationRepository.findByEmailIgnoreCaseAndDeletedIsFalse(userName);
		return Optional.ofNullable(user).map(UserDetailImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("Not found" + userName));
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new PasswordEncoderImpl();
	}
}
