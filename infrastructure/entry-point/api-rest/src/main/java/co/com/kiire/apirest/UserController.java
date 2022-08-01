package co.com.kiire.apirest;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.apirest.dto.SaveUserDto;
import co.com.kiire.apirest.dto.UserDTO;
import co.com.kiire.apirest.util.HandlerErrorController;
import co.com.kiire.apirest.util.mapper.UserApiRestMapper;
import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.usecase.GetUsersUseCase;
import co.com.kiire.usecase.SaveUserUseCase;
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
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Tag(name = "UserController", description = "Grupo de Apis Rest de apis REST de usuarios en el dominio de onboarding.")
public class UserController {

    private static final Logger log = Loggers.getLogger(UserController.class.getName());

    private final SaveUserUseCase userUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final UserApiRestMapper userApiRestMapper;

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agregar usuario", description = "Permite recibir una petición de agregar un usuario. Este evalua los campos obligatorios, existencia y formatos para antes de crear el elemento en el sistema")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto JSON con la informacion del usuario", required = true, content = @Content(schema = @Schema(implementation = SaveUserDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario Creado"),
            @ApiResponse(responseCode = "404", description = "Los datos recibidos no cumplen con la obligatoriedad o formatos esperados", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))})
    public Mono<GenericResponseDTO<UserDTO>> saveUser(@RequestBody SaveUserDto saveUserDto) {
        HandlerErrorController<UserDTO> handlerErrorController = new HandlerErrorController<>();
        return handlerErrorController.addErrors(Mono.just(saveUserDto)
                .doOnSuccess(request -> log.debug("Init saveUser with request {}", request))
                .map(this.userApiRestMapper::saveUserDtoToUser)
                .flatMap(this.userUseCase::execute)
                .map(this.userApiRestMapper::userToUserDto)
                .map(userDto -> new GenericResponseDTO<>(ResponseCode.KAR001, userDto))
                .doOnSuccess(response -> log.debug("Finish saveUser with response {}", response)), "saveUser");
    }

    @GetMapping(value = "/users")
    @Operation(summary = "Obtener usuarios por nombre", description = "Permite recibir una petición de obtener usuarios por nombre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos"),
            @ApiResponse(responseCode = "404", description = "Los datos recibidos no cumplen con la obligatoriedad o formatos esperados", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))})
    public Mono<GenericResponseDTO<List<UserDTO>>> getUsers(
            @Parameter(name = "name", description = "Nombre de los usuarios", required = true, in = ParameterIn.QUERY) @RequestParam String name) {
        HandlerErrorController<List<UserDTO>> handlerErrorController = new HandlerErrorController<>();
        return handlerErrorController.addErrors(Mono.just(name)
                .doOnSuccess(request -> log.debug("Init saveUser with request {}", request))
                .flatMapMany(this.getUsersUseCase::execute)
                .map(this.userApiRestMapper::userToUserDto)
                .collectList()
                .map(userDto -> new GenericResponseDTO<>(ResponseCode.KAR001, userDto))
                .doOnSuccess(response -> log.debug("Finish saveUser with response {}", response)), "saveUser");
    }

}
