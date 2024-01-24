package co.com.jineteapp.apirest.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtTokenAuthenticationFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        String secretKey = jwtConfig.getSecretKey();

        logger.info("doFilter called. Secret key is null: {}", secretKey == null);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();

                if (username != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    logger.info("JWT Token successfully validated for user: {}", username);
                } else {
                    logger.warn("JWT Token does not contain a valid subject.");
                }

            } catch (Exception e) {
                logger.error("Error validating JWT Token", e);
                SecurityContextHolder.clearContext();
            }
        } else {
            logger.warn("Authorization header is missing or does not contain a Bearer token.");
        }

        filterChain.doFilter(request, response);
    }
}
