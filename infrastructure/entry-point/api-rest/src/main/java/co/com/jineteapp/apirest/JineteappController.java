package co.com.jineteapp.apirest;

import co.com.jineteapp.apirest.dto.*;
import co.com.jineteapp.apirest.handler.AuthHandler;
import co.com.jineteapp.apirest.handler.CreditCardHandler;
import co.com.jineteapp.apirest.handler.UserHandler;
import co.com.jineteapp.apirest.handler.TransactionHandler;
import co.com.jineteapp.model.User;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Tag(name = "JineteappController", description = "Group of Api RESTs that are exposed to communicate front and back")
public class JineteappController {
    private final UserHandler userHandler;
    private final CreditCardHandler creditCardHandler;
    private final TransactionHandler transactionHandler;
    private final AuthHandler authHandler;

    @GetMapping(value = "/user/{id}")
    @Operation(summary = "Endpoint to get a user", description = "This endpoint will return a user object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<UserDto>> getUser(
    @Parameter(name = "id", description = "User id", required = true, in = ParameterIn.PATH) @PathVariable  Integer id) {
        return this.userHandler.getUserById(id);
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
    @Operation(summary = "Endpoint to save add a new credit card", description = "This endpoint will return a response that validates the new credit card status")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object with the information of the new credit card", required = true, content = @Content(schema = @Schema(implementation = SaveCreditCardDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<Boolean>> saveCreditCard(@RequestBody SaveCreditCardDto saveCreditCardDto){
        return this.creditCardHandler.saveCreditCard(saveCreditCardDto);
    }
    @PostMapping(value = "/new-transaction")
    @Operation(summary = "Endpoint to save add a new transaction", description = "This endpoint will return a response that validates the new transaction status")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object with the information of the new transaction", required = true, content = @Content(schema = @Schema(implementation = SaveTransactionDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<Boolean>> saveTransaction(@RequestBody SaveTransactionDto saveTransactionDto){
        return this.transactionHandler.saveTransaction(saveTransactionDto);
    }
    @GetMapping(value = "/transaction-by-user/{userId}")
    @Operation(summary = "Endpoint to choose find transactions by its user id", description = "This endpoint will return a transaction object to use")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Flux<GenericResponseDto<TransactionDto>> getTransactionByUserId(
            @Parameter(name = "user_id", description = "User id", required = true, in = ParameterIn.PATH) @PathVariable  Integer userId) {
        return this.transactionHandler.getTransactionByUserId(userId);
    }
    @GetMapping(value = "/transaction-by-credit-card/{creditCardId}")
    @Operation(summary = "Endpoint to choose find transactions by its credit card id", description = "This endpoint will return a transaction object to use")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Flux<GenericResponseDto<TransactionDto>> getTransactionByCreditCardId(
            @Parameter(name = "creditCardId", description = "Credit card id", required = true, in = ParameterIn.PATH) @PathVariable  Integer creditCardId) {
        return this.transactionHandler.getTransactionByCreditCardId(creditCardId);
    }
    @PostMapping(value = "/auth/login")
    @Operation(summary = "Endpoint to login to jineteapp", description = "This endpoint will return a confirmation for the login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<LoginDto>> login(@RequestBody LoginDto loginDto){
        return this.authHandler.login(loginDto);
    }
    @PostMapping(value = "/auth/register")
    @Operation(summary = "Endpoint to create a user", description = "This endpoint will return a response that validates the new user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object with the information of the new user", required = true, content = @Content(schema = @Schema(implementation = UserDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = String.class)))})
    public Mono<GenericResponseDto<UserDto>> saveUser(@RequestBody UserDto userDto){
        return this.userHandler.saveUser(userDto);
    }

}
