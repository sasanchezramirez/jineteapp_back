package co.com.jineteapp.apirest.mapper;

import co.com.jineteapp.apirest.dto.CreditCardDto;
import co.com.jineteapp.apirest.dto.SaveCreditCardDto;
import co.com.jineteapp.apirest.dto.UserDto;
import co.com.jineteapp.model.CreditCard;
import co.com.jineteapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Mono;

@Mapper
public interface JineteappRestApiMapper {
    UserDto userToUserDto(User user);
    CreditCardDto creditCardToCreditCardDto(CreditCard creditCard);
    @Mapping(target = "id", ignore = true)
    CreditCard saveCreditCardDtoToCreditCard(SaveCreditCardDto saveCreditCardDto);
}
