package com.ey.javatest.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.stereotype.Component;

import com.ey.javatest.data.dto.JwtObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	public String generateToken(Authentication authentication) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		JwtObject userPrincipal = (JwtObject) authentication.getPrincipal();
		Claims claims = Jwts.claims();

		claims.put("authorities", mapper.writeValueAsString(authentication.getAuthorities()));
		claims.put("name", userPrincipal.getName());
		claims.put("mail", userPrincipal.getUsername());
		claims.put("id", userPrincipal.getId());
		Date expiryDate = new Date(System.currentTimeMillis() + 600000l);
		return Jwts.builder().setClaims(claims).setSubject(userPrincipal.getUsername().toString())
				.setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, "com.ey.javatest.key")
				.compact();
	}


	public JwtObject getUserPrincipalFromToken(String token) throws IOException {
		Claims claims = getClaims(token);
		JwtObject result = new JwtObject();
		result.setAuthorities(getAuth(token));
		result.setName(findKeyClaimsInData("name", claims));
		result.setUsername(findKeyClaimsInData("mail", claims));
		result.setId(findKeyClaimsInData("id", claims));

		return result;
	}

	public boolean validate(String authToken) {
		try {
			Jwts.parser().setSigningKey("com.ey.javatest.key").parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			ex.printStackTrace();
		} catch (ExpiredJwtException ex) {
			ex.printStackTrace();
		} catch (UnsupportedJwtException ex) {
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	private String findKeyClaimsInData(String value, Claims claims) {
		return claims.get(value, String.class);
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey("com.ey.javatest.key").parseClaimsJws(token).getBody();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey("com.ey.javatest.key").parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public String getNameFromJWT(String token) {
		Claims claims = getClaims(token);
		return findKeyClaimsInData("name", claims);
	}

	public String getUsernameFromJWT(String token) {
		Claims claims = getClaims(token);
		return findKeyClaimsInData("mail", claims);
	}

	public Collection<? extends GrantedAuthority> getAuth(String token) throws IOException {
		Claims claims = getClaims(token);
		String auth = findKeyClaimsInData("authorities", claims);
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		if(auth != null && !auth.isEmpty()) {
			authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(auth.getBytes(), SimpleGrantedAuthority[].class));
		}
		return authorities;
	}

	
}
