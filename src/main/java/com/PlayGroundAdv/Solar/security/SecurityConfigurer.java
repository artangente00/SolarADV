package com.PlayGroundAdv.Solar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	public static final String[] AUTHORIZED_PATHS = new String[]{
			"/",
			"/*.html",
			"/favicon.ico",
			"/**/*.html",
			"/**/*.css",
			"/**/*.js",
			"/**/*.png",
			"/**/*.json",
			"/*.json",
			"/**/*.svg",
			"/**/*.woff*","/**/*.ttf",
			"/login/getLogin",
			"/login/resetPasswrod",
			"/login/verifyPassword",
			"/login/resetFinalStep",
			"/signup/setUser",
			"/signup/verification",
			"/signup/setUserNotAllow",
			"/user/getUserSettingAccess",
			"/user/getUserRole",
			"/login/getAllPermitForChart",
			"/dashboard/getSystemSizeReports",
			"/adv-stomp",
			"/adv-stomp/**",
			"/utils/archiveid",
			"/utils/groundRailRackingRsheet"

	};

	@Autowired
	private Environment env;
	private final UserDetailsServiceImpl userDetailService;
	private final JWTRequestFilter jwtRequestFilter;

	public SecurityConfigurer(UserDetailsServiceImpl userDetailService, JWTRequestFilter jwtRequestFilter) {
		this.userDetailService = userDetailService;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
				.passwordEncoder(new PasswordEncoderImpl());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
				.csrf().disable()
				.requiresChannel().anyRequest().requiresSecure()
				.and().authorizeRequests().antMatchers(AUTHORIZED_PATHS).permitAll()
				.antMatchers("/adv-stomp").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "**").permitAll()//allow CORS option calls
				.anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final boolean secure = Boolean.parseBoolean(env.getProperty("server.tomcat.remoteip.protocol-header-https-value"));
		final String protocol = secure ? "https://" : "http://";
		final String remoteHost = env.getProperty("REMOTE_HOST");
		final String remotePort = env.getProperty("REMOTE_PORT");
//		final String origin = protocol + remoteHost + ":" + remotePort;
                final String origin = "https://client.advancedsolarpermits.com";

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList(origin));
		configuration.setAllowedMethods(Arrays.asList(
				HttpMethod.HEAD.name(),
				HttpMethod.PATCH.name(),
				HttpMethod.OPTIONS.name(),
				HttpMethod.PUT.name(),
				HttpMethod.POST.name(),
				HttpMethod.GET.name(),
				HttpMethod.DELETE.name()
		));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(
				Arrays.asList("Authorization",
						"Cache-Control",
						"Content-Type",
						"Access-Control-Allow-Origin",
						"Access-Control-Expose-Headers",
						"Access-Control-Allow-Headers"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
