package co.com.kiire.persistence.service;

import co.com.kiire.gateway.contract.SaveUserGateway;
import co.com.kiire.model.User;
import co.com.kiire.persistence.mapper.UserPersistenceMapper;
import co.com.kiire.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@EnableR2dbcRepositories(basePackageClasses = UserRepository.class)
public class SaveUserService implements SaveUserGateway {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Mono<User> saveUser(User user) {
        return Mono.just(user)
                .map(this.userPersistenceMapper::userToUserDto)
                .flatMap(this.userRepository::save)
                .map(this.userPersistenceMapper::userDtoToUser);
    }
}
