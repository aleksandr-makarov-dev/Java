package org.example.tasklist.web.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // read bearer token (access) from Authorization header
        // we must cast servelRequest to HttpServletRequest to be able to access getHeader method
        String bearerToken = ((HttpServletRequest)servletRequest).getHeader("Authorization");

        // check if bearer token is not null and it starts with "Bearer "
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){

            // cut the "Bearer " part from token to get clean access token
            String accessToken = bearerToken.substring(7);

            // validate access token
            if(accessToken != null && jwtProvider.validateToken(accessToken)){
                try{

                    // try to get authentication
                    Authentication authentication = jwtProvider.getAuthentication(accessToken);

                    // if authentication is not null set it to SecurityContextHolder
                    // it means that user is authenticated
                    if(authentication != null){
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }catch (Exception ignored){

                }

                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }
}
