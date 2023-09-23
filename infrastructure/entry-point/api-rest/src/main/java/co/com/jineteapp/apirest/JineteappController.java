package co.com.jineteapp.apirest;

import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.UserDto;
import co.com.jineteapp.apirest.handler.GetUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Tag(name = "UserController", description = "Grupo de Apis Rest de apis REST de usuarios en el dominio de onboarding.")
public class JineteappController {
    private final GetUserHandler getUserHandler;

    @GetMapping(value = "/test")
    @Operation(summary = "Endpoint de prueba", description = "Este endpoint simplemente devuelve un string de respuesta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<String> testEndpoint() {
        return Mono.just("Respuesta de prueba");
    }

    @GetMapping(value = "/user/{id}")
    @Operation(summary = "Endpoint de prueba", description = "Este endpoint simplemente devuelve un string de respuesta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<UserDto>> getUser(
    @Parameter(name = "id", description = "User id", required = true, in = ParameterIn.PATH) @PathVariable  Integer id) {
        return this.getUserHandler.getUserById(id);
    }

}
