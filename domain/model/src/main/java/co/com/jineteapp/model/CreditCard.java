package co.com.jineteapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCard {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer availability;
    private Integer balance;
    private String datelineToPay;
}
