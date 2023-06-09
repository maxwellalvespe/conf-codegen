package com.api.security.conf;

import com.api.security.auth.TokenServiceImpl;
import com.api.security.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@Component
public class FilterLog extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(FilterLog.class);

    @Autowired
    private TokenServiceImpl tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //todo sempre que for feita uma requisição passará primeiro nesse filter

        String getTokenValue;
        var getAuthorizationHeader = request.getHeader("Authorization");
        if (nonNull(getAuthorizationHeader)) {

            getTokenValue = getAuthorizationHeader.replace("Bearer ", "");
            var obtentoOAssunto = this.tokenService.getSubject(getTokenValue);

            var getUserFakeRepository = new Usuario("maxwell", "123");
            var authenticationToken = new UsernamePasswordAuthenticationToken(getUserFakeRepository, null, getUserFakeRepository.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request, response);
    }

//    @Override   //todo implementação utilizando o filter do javax.servelet;
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        logger.info("Hello from: " + request.getLocalAddr());
//        logger.info("Validation: " + request.getServerName());
//        logger.info("Validation: " + request.getServletContext());
//        chain.doFilter(request, response);
//    }
}
