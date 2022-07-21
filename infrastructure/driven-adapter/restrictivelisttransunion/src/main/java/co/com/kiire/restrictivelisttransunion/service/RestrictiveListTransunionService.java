package co.com.kiire.restrictivelisttransunion.service;

import co.com.kiire.gateway.contract.RestrictiveListGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestrictiveListTransunionService implements RestrictiveListGateway {
    private final List<String> forbidden;

    @Override
    public boolean validateList(String numberIdentification) {
        if (this.forbidden.isEmpty()) {
            this.forbidden.add("1234567890");
            this.forbidden.add("0987654321");
            this.forbidden.add("1111111111");
        }
        return !this.forbidden.contains(numberIdentification);
    }
}
