package co.com.jineteapp.apirest.mapper;

import co.com.jineteapp.apirest.dto.*;
import co.com.jineteapp.model.CreditCard;
import co.com.jineteapp.model.Login;
import co.com.jineteapp.model.Transaction;
import co.com.jineteapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Mono;

@Mapper
public interface JineteappRestApiMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    CreditCardDto creditCardToCreditCardDto(CreditCard creditCard);
    @Mapping(target = "id", ignore = true)
    CreditCard saveCreditCardDtoToCreditCard(SaveCreditCardDto saveCreditCardDto);
    @Mapping(target = "id", ignore = true)
    Transaction saveTransactionDtoToTransaction(SaveTransactionDto saveTransactionDto);
    TransactionDto transactionToTransactionDto(Transaction transaction);
    Login loginDtoToLogin(LoginDto loginDto);
}
