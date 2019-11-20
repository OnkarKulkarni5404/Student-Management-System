package com.StudentClient.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.StudentClient.beans.Token;
import com.StudentClient.repo.TokenRepo;

public class Jwtauthfilter extends OncePerRequestFilter {

	@Autowired
	private TokenRepo tokenrepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		// Fetching the authorization header from the request.
		String authenticationHeader= request.getHeader(Iconstants.HEADER);
		
		
		
		System.out.println("bc");
		System.out.println(authenticationHeader);
		SecurityContext context= SecurityContextHolder.getContext();

			if(authenticationHeader != null && authenticationHeader.startsWith("Bearer")) {

				final String bearerTkn= authenticationHeader.replaceAll(Iconstants.BEARER_TOKEN, "");
				System.out.println("Following token is received from the protected url= "+ bearerTkn);

				// Parsing the jwt token.
					Jws<Claims> claims = Jwts.parser().requireIssuer(Iconstants.ISSUER).setSigningKey(Iconstants.SECRET_KEY).parseClaimsJws(bearerTkn);

					// Obtaining the claims from the parsed jwt token.
					String user= (String) claims.getBody().get("usr");
					String roles= (String) claims.getBody().get("rol");

					// Creating the list of granted-authorities for the received roles.
					List<GrantedAuthority> authority= new ArrayList<GrantedAuthority>();
					for(String role: roles.split(","))
						authority.add(new SimpleGrantedAuthority(role));

					// Creating an authentication object using the claims.
					Myauthtoken authenticationTkn= new Myauthtoken(user, null, authority);
					// Storing the authentication object in the security context.
					context.setAuthentication(authenticationTkn);
				
			}

			filterChain.doFilter(request, response);
			context.setAuthentication(null);
		
	}
}
