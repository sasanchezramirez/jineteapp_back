package co.com.jineteapp.persistence.repository;

import co.com.jineteapp.persistence.entity.TransactionEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends R2dbcRepository<TransactionEntity, Integer> {
    Flux<TransactionEntity> findTransactionByUserId(Integer userId);
}
