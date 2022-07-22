package co.com.kiire.persistence.service;

import co.com.kiire.gateway.contract.SaveUserGateway;
import co.com.kiire.model.User;
import co.com.kiire.persistence.mapper.UserMapper;
import co.com.kiire.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveUserService implements SaveUserGateway {

    private final UserRepository userRepository;

    @Override
    public Mono<User> saveUser(User user) {
        return Mono.just(user)
                .map(UserMapper::userToUserDto)
                .flatMap(this.userRepository::save)
                .map(UserMapper::userDtoToUser);
    }
}
