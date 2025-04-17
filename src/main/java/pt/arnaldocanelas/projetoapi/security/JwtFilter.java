package pt.arnaldocanelas.projetoapi.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pt.arnaldocanelas.projetoapi.dto.CustomErrorDTO;
import pt.arnaldocanelas.projetoapi.services.CustomUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
		this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException, ExpiredJwtException{

        try {
	    	String authHeader = request.getHeader("Authorization");
	
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            String token = authHeader.substring(7);
	            String username = jwtUtil.extractUsername(token);
	
	            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	
	                if (jwtUtil.isTokenValid(token, userDetails)) {
	                    UsernamePasswordAuthenticationToken authToken =
	                            new UsernamePasswordAuthenticationToken(
	                                    userDetails, null, userDetails.getAuthorities());
	
	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authToken);
	                }
	            }
	        }
	 
	        chain.doFilter(request, response);
	        
        }catch (ExpiredJwtException e) {
        	
    		CustomErrorDTO err = new CustomErrorDTO(HttpStatus.UNAUTHORIZED.value(), "Token expirado. Por favor, autentique-se novamente.", request.getRequestURI());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(err));
            
        }
        catch (JwtException e) {
       		CustomErrorDTO err = new CustomErrorDTO(HttpStatus.UNAUTHORIZED.value(), "Invalid Jwt token.", request.getRequestURI());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(err));
        }
              
    }
}