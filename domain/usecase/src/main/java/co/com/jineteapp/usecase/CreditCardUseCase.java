package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.CreditCard;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
public class CreditCardUseCase {
    private static final Logger log = Loggers.getLogger(CreditCardUseCase.class.getName());
    private final PersistenceGateway persistenceGateway;

    public Mono<CreditCard> getCreditCardUseCase(Integer userId){
        log.debug("Initializing getCreditCardUseCase");
        return this.persistenceGateway.getCreditCardByUserId(userId);
    }

    public Mono<Boolean> saveCreditCardUseCase(CreditCard creditCard){
        log.debug("Initializing saveCreditCardUseCase");
        return this.persistenceGateway.saveCreditCard(creditCard);
    }

}
