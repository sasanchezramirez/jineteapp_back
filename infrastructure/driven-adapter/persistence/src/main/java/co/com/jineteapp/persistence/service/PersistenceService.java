package co.com.jineteapp.persistence.service;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.User;
import co.com.jineteapp.persistence.mapper.PersistenceMapper;
import co.com.jineteapp.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class PersistenceService implements PersistenceGateway {
    private final UserRepository userRepository;
    private final PersistenceMapper persistenceMapper;
    @Override
    public Mono<User> getUserById(Integer id) {
        return this.userRepository.findUserById(id)
                .map(this.persistenceMapper::userEntityToUser);
    }
}
