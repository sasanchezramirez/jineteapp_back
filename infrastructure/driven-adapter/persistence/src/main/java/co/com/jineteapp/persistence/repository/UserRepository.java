package co.com.jineteapp.persistence.repository;

import co.com.jineteapp.persistence.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<UserEntity, Integer> {
    Mono<UserEntity> findUserById(Integer id);
    Mono<UserEntity> findUserByEmail(String email);
    Mono<UserEntity> saveUser(UserEntity user);
}
