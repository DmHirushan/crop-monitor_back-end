package lk.ijse.crop_monitor.config;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.crop_monitor.service.JWTService;
import lk.ijse.crop_monitor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.lang3.StringUtils;

@Configuration
@RequiredArgsConstructor
public class JWTConfig extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        System.out.println("Bdu Awa..");
        String initToken = request.getHeader("Authorization");
        System.out.println(initToken);
        String userEmail;
        String jwtToken;

        //Initial validation
        if(StringUtils.isEmpty(initToken) || !initToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //Token received
        jwtToken = initToken.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);

        //userEmail validation
        if(StringUtils.isNotEmpty(userEmail) &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            //load user details based on the email
            var loadedUser =
                    userService.userDetailsService().loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwtToken, loadedUser)) {
                SecurityContext emptyContext =
                        SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetails(request));
                emptyContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(emptyContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
