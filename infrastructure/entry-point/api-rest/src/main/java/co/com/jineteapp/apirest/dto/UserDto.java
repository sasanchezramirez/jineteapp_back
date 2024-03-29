package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String accessToken;
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
}
