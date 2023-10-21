package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.Login;
import co.com.jineteapp.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.Objects;

@RequiredArgsConstructor
public class AuthUseCase {
    private static final Logger log = Loggers.getLogger(AuthUseCase.class.getName());
    private final PersistenceGateway persistenceGateway;

    public Mono<Boolean> execute(Login login){
        return this.persistenceGateway.getUserByEmail(login.getEmail())
                .map(user -> {
                    if (Objects.equals(user.getPassword(), login.getPassword()))
                        return true;
                });
    }
}
