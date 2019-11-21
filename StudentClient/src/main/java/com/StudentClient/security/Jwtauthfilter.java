package com.StudentClient.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.StudentClient.beans.Token;
import com.StudentClient.repo.TokenRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jwtauthfilter extends OncePerRequestFilter {

	@Autowired
	private TokenRepo tokenRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Fetching the authorization header from the request.
		String authenticationHeader = request.getHeader(Iconstants.HEADER);

		try {
			SecurityContext context = SecurityContextHolder.getContext();

			if (authenticationHeader != null
					&& authenticationHeader.startsWith("Bearer")) {

				String body = authenticationHeader.split("\\.")[1];
				byte[] decoded_byte = Base64.getDecoder().decode(body);
				String decoded_string = new String(decoded_byte);
				ObjectMapper mapper = new ObjectMapper();
				Map data = mapper.readValue(decoded_string, Map.class);
				System.out.println(data.toString());
				long exp = (long) data.get("exp");

				// accepting user name
				String userName = (String) data.get("usr");
				// authHeader is only the encrypted part excluding Bearer
				String authHeader = authenticationHeader.substring(6).trim();

				System.out.println(userName);
				System.out.println(authHeader);
				System.out.println(exp);

				Token tikTok = tokenRepo.findByToken(authHeader);
				

				try {
					if (exp < (new Date().getTime())) {
						throw new Exception();
					}else if (tikTok == null || !(tokenRepo.findByToken(authHeader).getUsername().equals(userName))) {//handling this case for using token even after its deletion
							throw new Exception();
					}
					else {
						final String bearerTkn = authenticationHeader
								.replaceAll(Iconstants.BEARER_TOKEN, "");
						System.out
								.println("Following token is received from the protected url= "
										+ bearerTkn);

						try {
							// Parsing the jwt token.
							Jws<Claims> claims = Jwts.parser()
									.requireIssuer(Iconstants.ISSUER)
									.setSigningKey(Iconstants.SECRET_KEY)
									.parseClaimsJws(bearerTkn);

							// Obtaining the claims from the parsed jwt token.
							String user = (String) claims.getBody().get("usr");
							String roles = (String) claims.getBody().get("rol");

							// Creating the list of granted-authorities for the
							// received
							// roles.
							List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
							for (String role : roles.split(","))
								authority.add(new SimpleGrantedAuthority(role));

							// Creating an authentication object using the
							// claims.
							Myauthtoken authenticationTkn = new Myauthtoken(
									user, null, authority);
							// Storing the authentication object in the security
							// context.
							context.setAuthentication(authenticationTkn);
						} catch (SignatureException e) {
							throw new ServletException("Invalid token.");
						}
					}
				} catch (Exception e) {
					System.out.println("Token Expired.");
				}

			}

			filterChain.doFilter(request, response);
			context.setAuthentication(null);
		} catch (AuthenticationException ex) {
			throw new ServletException("Authentication exception.");
		}
	}
}