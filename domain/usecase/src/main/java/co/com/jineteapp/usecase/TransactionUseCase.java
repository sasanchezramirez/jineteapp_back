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

    public Mono<Boolean> saveTransactionUseCase(Transaction transaction) {
        log.debug("Initializing saveTransactionUseCase");
        if (transaction.getTypeOfTransactionId() == 1) {
            return this.updateCreditCardBalanceWhenLosses(transaction)
                    .then(this.persistenceGateway.saveTransaction(transaction));
        } else if (transaction.getTypeOfTransactionId() == 2) {
            return this.updateCreditCardBalanceWhenPayment(transaction)
                    .then(this.persistenceGateway.saveTransaction(transaction));
        } else {
            return Mono.error(new IllegalArgumentException("Invalid transactionTypeId"));
        }
    }

    public Flux<Transaction> getTransactionByUserId(Integer userId){
        log.debug("Initializing getTransactionByUserId");
        return this.persistenceGateway.getTransactionByUserId(userId);
    }

    public Flux<Transaction> getTransactionCreditCardId(Integer creditCardId){
        log.debug("Initializing getTransactionCreditCardId");
        return this.persistenceGateway.getTransactionByCreditCardId(creditCardId);
    }

    public Mono<Void> updateCreditCardBalanceWhenPayment(Transaction transaction) {
        log.debug("It's a payment... Updating credit card balance");
        Integer userId = transaction.getUserId();

        return this.persistenceGateway.getCreditCardByUserId(userId)
                .flatMap(creditCard -> {
                    log.debug("Credit card founded with balance: ", creditCard.getBalance());
                    Integer newBalance = creditCard.getBalance() - transaction.getAmount();
                    log.debug("New balance: ", newBalance);
                    creditCard.setBalance(newBalance);
                    return this.persistenceGateway.saveCreditCard(creditCard);
                })
                .then();
    }

    public Mono<Void> updateCreditCardBalanceWhenLosses(Transaction transaction) {
        log.debug("There are losses... Updating credit card balance");
        Integer userId = transaction.getUserId();

        return this.persistenceGateway.getCreditCardByUserId(userId)
                .flatMap(creditCard -> {
                    Integer newBalance = creditCard.getBalance() + transaction.getLosses();
                    log.debug("New balance: ", newBalance);
                    creditCard.setBalance(newBalance);
                    return this.persistenceGateway.saveCreditCard(creditCard);
                })
                .then();
    }

}
