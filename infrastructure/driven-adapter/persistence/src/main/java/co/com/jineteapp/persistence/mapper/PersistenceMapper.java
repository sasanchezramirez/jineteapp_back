package co.com.jineteapp.persistence.mapper;

import co.com.jineteapp.model.CreditCard;
import co.com.jineteapp.model.Transaction;
import co.com.jineteapp.model.TypeOfJineteo;
import co.com.jineteapp.model.User;
import co.com.jineteapp.persistence.entity.CreditCardEntity;
import co.com.jineteapp.persistence.entity.TransactionEntity;
import co.com.jineteapp.persistence.entity.TypeOfJineteoEntity;
import co.com.jineteapp.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersistenceMapper {
    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);
    CreditCard creditCardEntityToCreditCard(CreditCardEntity creditCardEntity);
    CreditCardEntity creditCardToCreditCardEntity(CreditCard creditCard);
    TransactionEntity transactionToTransactionEntity(Transaction transaction);
    Transaction transactionEntityToTransaction(TransactionEntity transactionEntity);
    TypeOfJineteo typeOfJineteoEntityToTypeOfJineteo(TypeOfJineteoEntity typeOfJineteoEntity);
}
