package co.com.kiire.testintegracion;

import co.com.kiire.apirest.dto.SaveUserDto;
import co.com.kiire.app.MainApplication;
import co.com.kiire.gateway.contract.RestrictiveListGateway;
import co.com.kiire.model.User;
import co.com.kiire.model.config.ResponseCode;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ActiveProfiles("test")
@SpringBootTest(classes = MainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    ConnectionFactory connectionFactory;
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private RestrictiveListGateway restrictiveListGateway;

    @BeforeAll
    public static void initDataBase(@Autowired ConnectionFactory connectionFactory) {
        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);

        String query = "create table IF NOT EXISTS users (id IDENTITY NOT NULL PRIMARY KEY, name varchar(255) not null, code varchar(255) not null, password varchar(255) not null)";
        template.getDatabaseClient().sql(query).fetch().rowsUpdated().block();
    }

    @Test
    void saveUserTestWithSaveUserSuccess() {
        User user = new User();
        user.setId(1L);
        user.setName("nombreTest");
        user.setCode("007");
        user.setPassword("1234");
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setName(user.getName());
        saveUserDto.setCode(user.getCode());
        saveUserDto.setPassword(user.getPassword());

        Mockito.when(this.restrictiveListGateway.validateList(saveUserDto.getCode()))
                .thenReturn(true);

        WebTestClient.ResponseSpec responseSpec = this.webTestClient.post()
                .uri("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveUserDto))
                .exchange();
        responseSpec.expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.responseCode").isEqualTo(ResponseCode.KAUS001.name())
                .jsonPath("$.status").isEqualTo(ResponseCode.KAUS001.getStatus())
                .jsonPath("$.responseMessage").isEqualTo(ResponseCode.KAUS001.getHtmlMessage())
                .jsonPath("$.data.id").isNotEmpty()
                .jsonPath("$.data.name").isEqualTo(user.getName())
                .jsonPath("$.data.code").isEqualTo(user.getCode())
                .jsonPath("$.data.password").isEqualTo(user.getPassword());
    }

    @Test
    void saveUserTestWithUserInListRestrictive() {
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setName("nombreTest");
        saveUserDto.setCode("008");
        saveUserDto.setPassword("1234");

        Mockito.when(this.restrictiveListGateway.validateList(saveUserDto.getCode()))
                .thenReturn(false);

        WebTestClient.ResponseSpec responseSpec = this.webTestClient.post()
                .uri("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveUserDto))
                .exchange();
        responseSpec.expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo(ResponseCode.KAUS002.getStatus());
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
                .jsonPath("$.status").isEqualTo(ResponseCode.KAUS002.getStatus());
    }
}
