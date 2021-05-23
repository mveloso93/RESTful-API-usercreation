package com.ey.javatest.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ey.javatest.service.SecurityService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;
	SecurityService userServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = null;
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			jwt = bearerToken.substring(7, bearerToken.length());
		}

		if (jwt != null && tokenProvider.validate(jwt)) {

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					tokenProvider.getUserPrincipalFromToken(jwt), null, tokenProvider.getAuth(jwt));
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(req, resp);
	}

}
