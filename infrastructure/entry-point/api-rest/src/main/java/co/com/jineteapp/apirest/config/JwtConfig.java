package co.com.jineteapp.apirest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secretKey;

}
