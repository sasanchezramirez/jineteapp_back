package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.Login;
import co.com.jineteapp.model.User;
import co.com.jineteapp.model.error.InvalidCredentialsException;
import co.com.jineteapp.model.error.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
public class AuthUseCase {
    private static final Logger log = Loggers.getLogger(AuthUseCase.class.getName());
    private final PersistenceGateway persistenceGateway;
    private final BCryptPasswordEncoder passwordEncoder;


    public Mono<Login> execute(Login login){
        log.info("Init password validation");

        return this.persistenceGateway.getUserByEmail(login.getEmail())
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with email: " + login.getEmail())))
                .flatMap(user -> {
                    if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                        String token = generateToken(login);
                        login.setAccessToken(token);
                        login.setId(user.getId());
                        log.info("Password matches!");
                        return Mono.just(login);
                    } else {
                        log.info("Password do no match!");
                        return Mono.error(new InvalidCredentialsException("Invalid password"));

                    }
                });
    }

    public String generateToken(Login login) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Jwts.builder()
                .setSubject(login.getEmail())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(secretKey)
                .compact();
    }
}
