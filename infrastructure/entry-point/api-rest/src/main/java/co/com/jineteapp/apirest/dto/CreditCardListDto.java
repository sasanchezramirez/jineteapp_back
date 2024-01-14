package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CreditCardListDto {
    List<CreditCardDto> creditCardDtoList;

    public CreditCardListDto(List<CreditCardDto> creditCards) {
        this.creditCardDtoList = creditCards;
    }
}
