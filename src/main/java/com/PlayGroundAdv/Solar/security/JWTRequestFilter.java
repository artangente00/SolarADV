package com.PlayGroundAdv.Solar.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	@Value("${REMOTE_HOST}")
	private String remoteHost;

	@Value("${REMOTE_PORT}")
	private String remotePort;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	private final UserDetailsServiceImpl userDetailsService;
	private final JwtUtils jwtUtils;

	public JWTRequestFilter(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils) {
		this.userDetailsService = userDetailsService;
		this.jwtUtils = jwtUtils;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
									@NonNull HttpServletResponse httpServletResponse,
									@NonNull FilterChain filterChain) throws ServletException, IOException {

		if (!activeProfile.equals("test")
			&& (!remoteHost.equals(httpServletRequest.getServerName())
				|| Integer.parseInt(remotePort) != httpServletRequest.getServerPort())) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "Access is not allowed!");
		}

		String userName = null;
		String jwt = null;

		final boolean authenticationRequired = !Arrays.asList(SecurityConfigurer.AUTHORIZED_PATHS)
				.contains(httpServletRequest.getRequestURI()) 
				&& !httpServletRequest.getRequestURI().contains("bundle.js")
				&& !httpServletRequest.getRequestURI().contains(".css")
				&& !httpServletRequest.getRequestURI().contains(".woff")
				&& !httpServletRequest.getRequestURI().contains(".png")
                                && !httpServletRequest.getRequestURI().contains("/solaradv")//added
				&& !httpServletRequest.getRequestURI().contains("/adv-stomp");

		if (authenticationRequired) {
			jwt = Arrays.stream(httpServletRequest.getCookies())
					.filter(cookie -> cookie.getName().equals("JWT_TOKEN"))
					.findFirst().map(Cookie::getValue)
					.orElse(null);

			if (jwt != null) {
				try {
					userName = jwtUtils.extractUsername(jwt);
				} catch (ExpiredJwtException exception) {
					httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "The token is expired");
				}
			}
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (Boolean.TRUE.equals(jwtUtils.validateToken(jwt, userDetails))) {
				final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
