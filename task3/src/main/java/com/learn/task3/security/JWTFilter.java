package com.learn.task3.security;

import com.learn.task3.service.CustomUserDetailsService;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTFilter extends OncePerRequestFilter {

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  CustomUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain chain) throws ServletException, IOException {

    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwt = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);
      //if signature doesn't match with calculated -> SignatureException
      //if signature is not correct (can't parse) -> MalformedJwtException
      //if signature is expired -> ExpiredJwtException
      username = jwtUtil.extractUsername(jwt);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      String commaSeparatedListOfAuthorities = jwtUtil.extractAuthorities(jwt);
      List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
          commaSeparatedListOfAuthorities);
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(
              username, null, authorities);

      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

    }
    chain.doFilter(request, response);
  }

}
