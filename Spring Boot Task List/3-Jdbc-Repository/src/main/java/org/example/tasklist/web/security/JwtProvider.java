package org.example.tasklist.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.exception.AccessDeniedException;
import org.example.tasklist.domain.user.Role;
import org.example.tasklist.domain.user.User;
import org.example.tasklist.service.UserService;
import org.example.tasklist.service.properties.JwtProperties;
import org.example.tasklist.web.models.auth.JwtResponseModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    // configuration
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    // key used to sign tokens
    private SecretKey key;

    @PostConstruct
    public void init() {
        // initializing key in @PostConstruct because we need jwtProperties
        key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    // create access (jwt) token and store userId, username and role
    // expires in 1 hour
    public String createAccessToken(Long userId, String username, Set<Role> roles){

        // create claims - data that will be stored in access token
        Claims claims = Jwts
                .claims()
                .subject(username)
                .add("id",userId)
                .add("roles",resolveRoles(roles))
                .build();

        // create expiration date time
        Instant validity = Instant.now()
                .plus(jwtProperties.getAccess(), ChronoUnit.MILLIS);

        // create jwt token, set claims, expiration, sign it with key and build
        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    private List<Role> resolveRoles(Set<Role> roles){
        return new ArrayList<>(roles);
    }

    // a method for creating refresh token for user
    public String createRefreshToken(Long userId, String username){
        Claims claims = Jwts
                .claims()
                .subject(username)
                .add("id",userId)
                .build();

        Instant validity = Instant.now()
                .plus(jwtProperties.getRefresh(), ChronoUnit.MILLIS);

        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    // refresh both user tokens
    public JwtResponseModel refreshUserTokens(String refreshToken){
        JwtResponseModel jwtResponseModel = new JwtResponseModel();

        if(!validateToken(refreshToken)){
            throw new AccessDeniedException("Access denied");
        }

        Long userId = Long.valueOf(getId(refreshToken));

        User user = userService.getById(userId);
        jwtResponseModel.setId(userId);
        jwtResponseModel.setUsername(user.getUsername());
        jwtResponseModel.setAccessToken(createAccessToken(userId,user.getUsername(),user.getRoles()));
        jwtResponseModel.setRefreshToken(createRefreshToken(userId,user.getUsername()));

        return jwtResponseModel;
    }

    // validate token
    public boolean validateToken(final String token){
        // create parser and set verification key, build parser and parse token, return claims

        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        // for token payload get expiration date and check if token is expired
        return claims
                .getPayload()
                .getExpiration()
                .after(new Date());
    }

    // get any value with access token
    private <T> T getPayloadValue(String token, String name, Class<T> clazz){
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(name, clazz);
    }

    // get id with access token
    private String getId(String token){
        return getPayloadValue(token,"id", String.class);
    }

    // get username with access token
    private String getUsername(String token){
        return getPayloadValue(token,"username",String.class);
    }

    // get authentication data with access token
    public Authentication getAuthentication(final String token){
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return  new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
}
