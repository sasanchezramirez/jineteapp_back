package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

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
                return this.persistenceGateway.saveUser(user);
    }
}
