package com.example.wow_another_crud.infraestructure.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.wow_another_crud.config.SecurityConfig;
import com.example.wow_another_crud.exceptions.TokenIsAbsent;
import com.example.wow_another_crud.exceptions.UserNotFoundException;
import com.example.wow_another_crud.model.User;
import com.example.wow_another_crud.model.UserDetailsImpl;
import com.example.wow_another_crud.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.Authenticator;
import java.util.Arrays;

import static com.example.wow_another_crud.utils.HttpUtils.sendHttp;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (checkIfEndpointIsNotPublic(request)) {
                String token = recoveryToken(request);
                if (token != null) {
                    String subject = jwtTokenService.getSubjectFromToken(token);
                    User user = userRepository.findByEmail(subject).orElseThrow(UserNotFoundException::new);

                    UserDetailsImpl userDetails = new UserDetailsImpl(user);

                    // Q: What is authorities in a JWT authentication context?
                    // A: Roles, groups or permissions from the account. Used to manage access control to resources.
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new TokenIsAbsent();
                }
            }
            filterChain.doFilter(request, response);
            // TODO: Make all error messages configurable and localizated.
        } catch (TokenIsAbsent ex) {
            sendHttp(request, response, HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed: No token provided. Please include your authentication token.");
        } catch (JWTVerificationException ex) {
            sendHttp(request, response, HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed: Provided token is invalid or expired. Please provide a valid token.");
        } catch (BadCredentialsException ex) {
            sendHttp(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eita.");
        } catch (Exception ex) {
            sendHttp(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred while processing your request.");
        }


    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }

}
