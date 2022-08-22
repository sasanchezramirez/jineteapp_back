package co.com.kiire.restrictivelisttransunion.service;

import co.com.kiire.model.User;
import co.com.kiire.model.error.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class IdvGoTransunionServiceTest {

    @InjectMocks
    RestrictiveListTransunionService restrictiveListTransunionService;

    @Mock
    List<String> forbidden;

    @Test
    void validateListWithSuccess() {
        User user = new User();
        user.setCode("test");
        user.setName("test");
        user.setPassword("test");

        StepVerifier.create(this.restrictiveListTransunionService.validateList(user))
                .expectNextMatches(response -> response.getCode().equalsIgnoreCase(user.getCode()))
                .expectComplete()
                .verify();
    }

    @Test
    void validateListWithUserFoundInRestrictiveList() {
        User user = new User();
        user.setCode("test2");
        user.setName("test2");
        user.setPassword("test2");

        Mockito.when(this.forbidden.contains(user.getCode())).thenReturn(true);

        StepVerifier.create(this.restrictiveListTransunionService.validateList(user))
                .expectError(CustomException.class)
                .verify();
    }
}
