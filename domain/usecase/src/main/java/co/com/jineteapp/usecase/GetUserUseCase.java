package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetUserUseCase {
    private final PersistenceGateway persistenceGateway;
    public Mono<User> execute(Integer id){
        return this.persistenceGateway.getUserById(id);
    }
}
