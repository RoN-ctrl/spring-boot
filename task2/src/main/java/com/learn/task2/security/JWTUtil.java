package com.learn.task2.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTUtil {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.sessionTime}")
  private long sessionTime;

  // generate token (put inside username and authorities)
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    String commaSeparatedListOfAuthorities = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
    claims.put("authorities", commaSeparatedListOfAuthorities);
    return createToken(claims, userDetails.getUsername());
  }

  //extract username from token (validate token inside)
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  //extract authorities (validate token inside)
  public String extractAuthorities(String token) {
    Function<Claims, String> claimsListFunction = claims -> (String) claims.get("authorities");
    return extractClaim(token, claimsListFunction);
  }


  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }


  private String createToken(Map<String, Object> claims, String subject) {

    return Jwts.builder().setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(expireTimeFromNow())
        .signWith(SignatureAlgorithm.HS256, secretKey).compact();
  }


  private Date expireTimeFromNow() {
    return new Date(System.currentTimeMillis() + sessionTime);
  }
}