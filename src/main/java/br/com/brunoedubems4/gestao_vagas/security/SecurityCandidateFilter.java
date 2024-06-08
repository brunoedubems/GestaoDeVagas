package br.com.brunoedubems4.gestao_vagas.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.brunoedubems4.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {
    @Autowired
    private JWTCandidateProvider jwtCandidateProvider;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                // SecurityContextHolder.getContext().setAuthentication(null);
                String header = request.getHeader("autorization");

                if(request.getRequestURI().startsWith("/candidate")){

                    
                    if(header != null){
                        var token = this.jwtCandidateProvider.validateToken(header);
                        
                        if(token ==null){
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                        
                        request.setAttribute("candidate_id", token.getSubject());
                        var roles = token.getClaim("roles");
                        
                        var grants = ((Collection<Object>) roles).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString())
                        ).toList();


                      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        token.getSubject(),
                           grants);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
                    
                filterChain.doFilter(request, response);


    }
    
}
