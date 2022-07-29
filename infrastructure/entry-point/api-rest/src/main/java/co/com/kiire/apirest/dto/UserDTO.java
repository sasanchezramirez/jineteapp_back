package co.com.kiire.apirest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "Modelo de salida de crear un usuario")
public class UserDTO {

    @Schema(description = "Id del usuario")
    private Integer id;

    @Schema(description = "Nombre del usuario")
    private String name;

    @Schema(description = "Código del usuario")
    private String code;

    @Schema(description = "Contraseña del usuario")
    private String password;

}
