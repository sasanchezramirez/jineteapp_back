package co.com.kiire.gateway.contract;

import co.com.kiire.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz necesaria para guardar un usuario
 */
public interface UserGateway {
    Mono<User> saveUser(User user);
    Flux<User> getUsers(String name);
}
