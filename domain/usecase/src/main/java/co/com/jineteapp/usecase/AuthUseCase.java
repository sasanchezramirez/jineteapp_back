package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.Login;
import co.com.jineteapp.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
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

    public Mono<Login> execute(Login login){
        log.info("Init password validation");
        return this.persistenceGateway.getUserByEmail(login.getEmail())
                .flatMap(user -> {
                    if(Objects.equals(user.getPassword(), login.getPassword())) {
                        String token = generateToken(login);
                        login.setAccessToken(token);
                        return Mono.just(login);
                    } else {
                        return Mono.empty();
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
