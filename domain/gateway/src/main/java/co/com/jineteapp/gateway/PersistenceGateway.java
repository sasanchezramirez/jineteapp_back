package co.com.jineteapp.gateway;

import co.com.jineteapp.model.CreditCard;
import co.com.jineteapp.model.Transaction;
import co.com.jineteapp.model.User;
import reactor.core.publisher.Mono;

/**
 * Interface used to communicate domain layer with database
 */
public interface PersistenceGateway {
    /**
     * Gets a user from the database
     *
     * @param id user's id
     * @return Users from the database
     */
    Mono<User> getUserById(Integer id);
    /**
     * Gets a credit card from the database
     *
     * @param userId parameter used to relate a credit card with a user
     * @return Users from the database
     */
    Mono<CreditCard> getCreditCardByUserId(Integer userId);
    /**
     * Saves a credit card from the database
     *
     * @param creditCard Object to be saved on the database
     * @return Boolean that confirms that a credit card was saved
     */
    Mono<Boolean> saveCreditCard(CreditCard creditCard);
    Mono<Boolean> saveTransaction(Transaction transaction);

}
