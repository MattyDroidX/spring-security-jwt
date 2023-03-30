package com.security.jwt.security.jwt;

import com.security.jwt.security.service.UserDetailsServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
    public static final String BEARER = "Bearer ";
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if(jwt != null && jwtTokenUtil.validateJwtToken(jwt)){
                String username = jwtTokenUtil.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authetication = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                authetication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authetication);
            }
        }catch (Exception e){
            log.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request,response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER))
            return headerAuth.substring(BEARER.length());


        return null;
    }


}
