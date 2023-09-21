package co.com.kiire.testintegracion;

import co.com.jineteapp.app.MainApplication;
import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.User;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@ActiveProfiles("test")
@SpringBootTest(classes = MainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    ConnectionFactory connectionFactory;
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private PersistenceGateway persistenceGateway;

    @BeforeAll
    public static void initDataBase(@Autowired ConnectionFactory connectionFactory) {
        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);

        String query = "create table IF NOT EXISTS users " +
                "(id IDENTITY NOT NULL PRIMARY KEY" +
                ", name varchar(255) not null" +
                ", email varchar(255) not null)";
        template.getDatabaseClient().sql(query).fetch().rowsUpdated().block();
    }

    @Test
    void getUserTestWithSucces() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setEmail("test@email.com");

        Mockito.when(this.persistenceGateway.getUserById(ArgumentMatchers.argThat(usr -> usr.getClass()..getCode()))))
                .thenReturn(Mono.just(user));

        WebTestClient.ResponseSpec responseSpec = this.webTestClient.post()
                .uri("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveUserDto))
                .exchange();
        responseSpec.expectStatus()
                .isOk();
    }

    @Test
    void saveUserTestWithUserInListRestrictive() {
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setName("nombreTest");
        saveUserDto.setCode("008");
        saveUserDto.setPassword("1234");

        Mockito.when(this.restrictiveListGateway.validateList(ArgumentMatchers.argThat(usr -> usr.getCode().equalsIgnoreCase(saveUserDto.getCode()))))
                .thenReturn(Mono.error(new CustomException(ResponseCode.KAR003)));

        WebTestClient.ResponseSpec responseSpec = this.webTestClient.post()
                .uri("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveUserDto))
                .exchange();
        responseSpec.expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo(ResponseCode.KAR003.getStatus());
    }

    @Test
    void saveUserTestWithoutFieldCode() {
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setName("nombreTest");
        saveUserDto.setPassword("1234");

        WebTestClient.ResponseSpec responseSpec = this.webTestClient.post()
                .uri("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveUserDto))
                .exchange();
        responseSpec.expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo(ResponseCode.KAR002.getStatus());
    }

    @Test
    void getUsersTestWithSuccess() {
        WebTestClient.ResponseSpec responseSpec = this.webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api/v1/users")
                                .queryParam("name", "nombreTest")
                                .build())
                .exchange();
        responseSpec.expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.responseCode").isEqualTo(ResponseCode.KAR001.name())
                .jsonPath("$.status").isEqualTo(ResponseCode.KAR001.getStatus())
                .jsonPath("$.responseMessage").isEqualTo(ResponseCode.KAR001.getHtmlMessage())
                .jsonPath("$.data").isArray();
    }
}
