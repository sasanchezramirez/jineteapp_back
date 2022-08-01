package co.com.kiire.persistence.service;

import co.com.kiire.gateway.contract.UserGateway;
import co.com.kiire.model.User;
import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.model.error.CustomException;
import co.com.kiire.persistence.mapper.UserPersistenceMapper;
import co.com.kiire.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

//persistencia no tiene log de inicio y final, solo de error
@Service
@AllArgsConstructor
@EnableR2dbcRepositories(basePackageClasses = UserRepository.class)
public class UserService implements UserGateway {

    private static final Logger log = Loggers.getLogger(UserService.class.getName());

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Mono<User> saveUser(User user) {
        return Mono.just(user)
                .map(this.userPersistenceMapper::userToUserEntity)
                .flatMap(this.userRepository::save)
                .map(this.userPersistenceMapper::userEntityToUser)
                .onErrorResume(exception -> {
                    log.debug("Error in saveUser with exception {}", exception.getMessage());
                    return Mono.error(new CustomException(ResponseCode.KAR000));
                });
    }

    @Override
    public Flux<User> getUsers(String name) {
        return this.userRepository.findAllByName(name)
                .map(this.userPersistenceMapper::userEntityToUser)
                .onErrorResume(exception -> {
                    log.debug("Error in getUsers with exception {}", exception.getMessage());
                    return Mono.error(new CustomException(ResponseCode.KAR000));
                });
    }
}
