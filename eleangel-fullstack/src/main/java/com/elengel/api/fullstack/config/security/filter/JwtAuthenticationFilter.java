package com.elengel.api.fullstack.config.security.filter;

import com.elengel.api.fullstack.exception.ObjectNotFoundException;
import com.elengel.api.fullstack.persistence.entity.security.User;
import com.elengel.api.fullstack.service.UserService;
import com.elengel.api.fullstack.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("entro al filtro jwt");

        //1 encabezado de la peticion

        String authorizationHeader = request.getHeader("Authorization");

        if(!StringUtils.hasText (authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            System.out.println("no fue autorizado?");
            return;
        }
        //2 obtener el jwt
        String jwt = authorizationHeader.split(" ")[1];

        //3 subject/username desde el token VALIDAR token firma y tenga la info
        String username = jwtService.extractUsername(jwt);
        System.out.println("el username sacado: "+username);
        //4 setear el objeto authentication
        User user = userService.findOneByUsername(username)
                .orElseThrow(()->new ObjectNotFoundException("El usuario no existe: "+username));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username,null, user.getAuthorities()
        );


        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);


        //5. ejecutar el registro filtros
        filterChain.doFilter(request,response);

    }
}
