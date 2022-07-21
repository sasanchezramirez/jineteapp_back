package co.com.kiire.gateway.contract;

import co.com.kiire.model.User;
import reactor.core.publisher.Mono;

public interface SaveUserGateway {
    Mono<User> saveUser(User user);
}
