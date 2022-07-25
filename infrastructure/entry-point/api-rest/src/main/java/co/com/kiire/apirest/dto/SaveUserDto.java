package co.com.kiire.apirest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "Modelo de entrada para crear un usuario")
public class SaveUserDto {

    @Schema(description = "Id del usuario", required = true)
    private String name;

    @Schema(description = "Código del usuario", required = true)
    private String code;

    @Schema(description = "Contraseña del usuario", required = true)
    private String password;
}
