package co.com.jineteapp.persistence.service;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.CreditCard;
import co.com.jineteapp.model.Transaction;
import co.com.jineteapp.model.User;
import co.com.jineteapp.persistence.entity.CreditCardEntity;
import co.com.jineteapp.persistence.entity.TransactionEntity;
import co.com.jineteapp.persistence.mapper.PersistenceMapper;
import co.com.jineteapp.persistence.repository.CreditCardRepository;
import co.com.jineteapp.persistence.repository.TransactionRepository;
import co.com.jineteapp.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Service
@AllArgsConstructor
public class PersistenceService implements PersistenceGateway {
    private static final Logger log = Loggers.getLogger(PersistenceService.class.getName());
    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;
    private final PersistenceMapper persistenceMapper;
    private final TransactionRepository transactionRepository;
    @Override
    public Mono<User> getUserById(Integer id) {
        log.debug("Using persistence gateway to find a user by its id");
        return this.userRepository.findUserById(id)
                .map(this.persistenceMapper::userEntityToUser);
    }

    @Override
    public Mono<CreditCard> getCreditCardByUserId(Integer userId) {
        log.debug("Using persistence gateway to find a credit card related to a user_id");
        return this.creditCardRepository.findCreditCardByUserId(userId)
                .map(this.persistenceMapper::creditCardEntityToCreditCard);
    }

    @Override
    public Mono<Boolean> saveCreditCard(CreditCard creditCard) {
        log.debug("Using persistence gateway to save a credit card");
        CreditCardEntity creditCardEntity = this.persistenceMapper.creditCardToCreditCardEntity(creditCard);
        return this.creditCardRepository.save(creditCardEntity)
                .flatMap(savedCreditCard -> {
                    log.debug("Credit card successfully added");
                    return Mono.just(Boolean.TRUE);
                })
                .onErrorResume(e -> {
                    log.error("An error occurred while saving the credit card: "+ e );
                    return Mono.just(Boolean.FALSE);
                });
    }

    @Override
    public Mono<Boolean> saveTransaction(Transaction transaction) {
        log.debug("Using persistence gateway to save a transaction");
        TransactionEntity transactionEntity = this.persistenceMapper.transactionToTransactionEntity(transaction);
        return this.transactionRepository.save(transactionEntity)
                .flatMap(savedTransaction -> {
                    log.debug("Transaction successfully added");
                    return Mono.just(Boolean.TRUE);
                })
                .onErrorResume(e -> {
                    log.error("An error occurred while saving the transaction: "+ e );
                    return Mono.just(Boolean.FALSE);
                });
    }
}
