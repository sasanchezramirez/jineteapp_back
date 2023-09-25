package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.Transaction;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
public class TransactionUseCase {
    private static final Logger log = Loggers.getLogger(TransactionUseCase.class.getName());
    private final PersistenceGateway persistenceGateway;

    public Mono<Boolean> saveTransactionUseCase(Transaction transaction){
        log.debug("Initializing saveTransactionUseCase");
        return this.persistenceGateway.saveTransaction(transaction);
    }

    public Flux<Transaction> getTransactionByUserId(Integer userId){
        log.debug("Initializing getTransactionByUserId");
        return this.persistenceGateway.getTransactionByUserId(userId);
    }
}
