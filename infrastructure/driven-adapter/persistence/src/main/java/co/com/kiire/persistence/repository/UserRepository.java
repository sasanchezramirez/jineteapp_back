package co.com.kiire.persistence.repository;

import co.com.kiire.persistence.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends R2dbcRepository<UserEntity, Long> {
    Flux<UserEntity> findAllByName(String name);
}
