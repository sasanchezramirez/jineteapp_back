package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
public class GetUserUseCase {
    private static final Logger log = Loggers.getLogger(GetUserUseCase.class.getName());
    private final PersistenceGateway persistenceGateway;
    public Mono<User> execute(Integer id){
        log.debug("Initializing getUserUseCase");
        return this.persistenceGateway.getUserById(id);
    }
}
