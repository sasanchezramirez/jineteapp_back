package co.com.kiire.apirest;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.apirest.dto.SaveUserDto;
import co.com.kiire.apirest.dto.UserDTO;
import co.com.kiire.apirest.handler.getUsersHandler;
import co.com.kiire.apirest.handler.saveUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Tag(name = "UserController", description = "Grupo de Apis Rest de apis REST de usuarios en el dominio de onboarding.")
public class UserController {

    private final saveUserHandler saveUserHandler;
    private final getUsersHandler getUsersHandler;

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agregar usuario", description = "Permite recibir una petición de agregar un usuario. Este evalua los campos obligatorios, existencia y formatos para antes de crear el elemento en el sistema")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto JSON con la informacion del usuario", required = true, content = @Content(schema = @Schema(implementation = SaveUserDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario Creado"),
            @ApiResponse(responseCode = "404", description = "Los datos recibidos no cumplen con la obligatoriedad o formatos esperados", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))})
    public Mono<GenericResponseDTO<UserDTO>> saveUser(@RequestBody SaveUserDto saveUserDto) {
        return this.saveUserHandler.saveUser(saveUserDto);
    }

    @GetMapping(value = "/users")
    @Operation(summary = "Obtener usuarios por nombre", description = "Permite recibir una petición de obtener usuarios por nombre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos"),
            @ApiResponse(responseCode = "404", description = "Los datos recibidos no cumplen con la obligatoriedad o formatos esperados", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))})
    public Mono<GenericResponseDTO<List<UserDTO>>> getUsers(
            @Parameter(name = "name", description = "Nombre de los usuarios", required = true, in = ParameterIn.QUERY) @RequestParam String name) {
        return this.getUsersHandler.getUsers(name);
    }

}
