package org.example.greenbay.Services.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.greenbay.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
  private final UserRepository userRepository;

  @Autowired
  public JwtServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Value("${JWT_SECRET_KEY}")
  private String JWT_SECRET_KEY;

  @Override
  public String extractUsername(String jwtToken) {
    return extractClaim(jwtToken, Claims::getSubject);
  }

  @Override
  public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(jwtToken);
    return claimsResolver.apply(claims);
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  @Override
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    Long id = userRepository.findByUsername(userDetails.getUsername()).getId();
    extraClaims.put("id", id);

    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        // valid for 24hours
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
    final String userName = extractUsername(jwtToken);
    return (userName.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
  }

  private boolean isTokenExpired(String jwtToken) {
    return extractExpiration(jwtToken).before(new Date());
  }

  private Date extractExpiration(String jwtToken) {
    return extractClaim(jwtToken, Claims::getExpiration);
  }

  private Claims extractAllClaims(String jwtToken) {
    return Jwts.parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(jwtToken)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
