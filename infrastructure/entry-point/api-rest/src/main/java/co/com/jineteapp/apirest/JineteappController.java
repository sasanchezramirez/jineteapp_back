package co.com.jineteapp.apirest;

import co.com.jineteapp.apirest.dto.CreditCardDto;
import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.SaveCreditCardDto;
import co.com.jineteapp.apirest.dto.UserDto;
import co.com.jineteapp.apirest.handler.CreditCardHandler;
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
@Tag(name = "UserController", description = "Group of Api RESTs that are exposed to communicate front and back")
public class JineteappController {
    private final GetUserHandler getUserHandler;
    private final CreditCardHandler creditCardHandler;

    @GetMapping(value = "/test")
    @Operation(summary = "Testing endpoint", description = "This endpoint is used to test the connection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<String> testEndpoint() {
        return Mono.just("Respuesta de prueba");
    }

    @GetMapping(value = "/user/{id}")
    @Operation(summary = "Endpoint de prueba", description = "This endpoint will return a user object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<UserDto>> getUser(
    @Parameter(name = "id", description = "User id", required = true, in = ParameterIn.PATH) @PathVariable  Integer id) {
        return this.getUserHandler.getUserById(id);
    }

    @GetMapping(value = "/credit-card/{userId}")
    @Operation(summary = "Endpoint to choose what credit card will be used", description = "This endpoint will return a creditCard object to use")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<CreditCardDto>> getCreditCard(
            @Parameter(name = "user_id", description = "User id", required = true, in = ParameterIn.PATH) @PathVariable  Integer userId) {
        return this.creditCardHandler.getCreditCard(userId);
    }
    @PostMapping(value = "/new-credit-card")
    @Operation(summary = "Endpoint to choose add a new credit card", description = "This endpoint will return a response that validates the new credit card status")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object with the information of the new credit card", required = true, content = @Content(schema = @Schema(implementation = SaveCreditCardDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<Boolean>> saveCreditCard(@RequestBody SaveCreditCardDto saveCreditCardDto){
        return this.creditCardHandler.saveCreditCard(saveCreditCardDto);
    }

}
