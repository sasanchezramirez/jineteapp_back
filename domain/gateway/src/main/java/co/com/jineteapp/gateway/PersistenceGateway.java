package co.com.jineteapp.gateway;

import co.com.jineteapp.model.User;
import reactor.core.publisher.Mono;

public interface PersistenceGateway {
    Mono<User> getUserById(Integer id);
}