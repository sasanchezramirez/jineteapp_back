package co.com.jineteapp.persistence.repository;

import co.com.jineteapp.persistence.entity.CreditCardEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardRepository extends R2dbcRepository<CreditCardEntity, Integer> {
    Flux<CreditCardEntity> findCreditCardByUserId(Integer userId);
}
