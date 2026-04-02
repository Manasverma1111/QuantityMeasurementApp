//package org.example.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.example.service.CustomUserDetailsService;
//import org.example.util.JwtUtil;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//    private final CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//
//        // CRITICAL FIX — skip public endpoints
//        if (path.startsWith("/api/auth") ||
//                path.startsWith("/oauth2") ||
//                path.startsWith("/login") ||
//                path.startsWith("/h2-console")) {
//
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = authHeader.substring(7);
//
////        try {
////            String email = jwtUtil.extractEmail(token);
////
////            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
////
////                if (jwtUtil.validateToken(token, userDetails)) {
////                    UsernamePasswordAuthenticationToken authToken =
////                            new UsernamePasswordAuthenticationToken(
////                                    userDetails, null, userDetails.getAuthorities());
////
////                    authToken.setDetails(new WebAuthenticationDetailsSource()
////                            .buildDetails(request));
////
////                    SecurityContextHolder.getContext().setAuthentication(authToken);
////                }
////            }
////        } catch (Exception e) {
////            // ignore invalid token
////        }
//
//        try {
//            String email = jwtUtil.extractEmail(token);
//            System.out.println("Extracted Email: " + email);
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//            System.out.println("User Loaded: " + userDetails.getUsername());
//
//            boolean isValid = jwtUtil.validateToken(token, userDetails);
//            System.out.println("Token Valid: " + isValid);
//
//            if (isValid) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails, null, userDetails.getAuthorities());
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            } else {
//                System.out.println("❌ Token validation failed");
//            }
//
//        } catch (Exception e) {
//            System.out.println("❌ JWT ERROR: " + e.getMessage());
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

package org.example.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.service.CustomUserDetailsService;
import org.example.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Skip public endpoints
        if (path.startsWith("/api/auth") ||
                path.startsWith("/oauth2") ||
                path.startsWith("/login") ||
                path.startsWith("/h2-console")) {

            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtUtil.extractEmail(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // IMPORTANT: validate BEFORE setting auth
                if (jwtUtil.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request));

                    // CRITICAL LINE
                    var context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);

                    System.out.println("AUTH SET FOR: " + email);
                } else {
                    System.out.println("TOKEN INVALID");
                }
            }

        } catch (Exception e) {
            System.out.println("JWT ERROR: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}