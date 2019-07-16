package service.impl.v2;

import service.AccountService;

import java.io.IOException;

public interface AccountServiceV2 extends AccountService {
    void updateAccountBalance(String accountNumner, String balance, String fileName) throws IOException;
}
