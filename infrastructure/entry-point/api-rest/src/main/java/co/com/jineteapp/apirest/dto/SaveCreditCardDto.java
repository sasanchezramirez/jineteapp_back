package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCreditCardDto {
    private Integer userId;
    private String name;
    private Integer availability;
    private Integer balance;
    private String datelineToPay;
}
