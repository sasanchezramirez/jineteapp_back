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

@RequiredArgsConstructor
public class UserUseCase {
    private static final Logger log = Loggers.getLogger(UserUseCase.class.getName());
    private final PersistenceGateway persistenceGateway;
    public Mono<User> executeGetUser(Integer id){
        log.debug("Initializing getUserUseCase");
        return this.persistenceGateway.getUserById(id);
    }

    public Mono<User> executeSaveUser(User user){
        log.debug("Initializing saveUserUseCase");

        return this.persistenceGateway.saveUser(user)
                .flatMap(userEntity -> {
                            userEntity.setAccessToken(this.generateToken(userEntity));
                            return Mono.just(userEntity);
                        }
                );
    }

    public String generateToken(User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(secretKey)
                .compact();
    }
}
