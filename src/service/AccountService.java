package service;

import model.Account;

import java.util.List;

public interface AccountService {
    List<Account> createAccount();

    Account getAccountByAccNumber(String accNumber);

    boolean isValidPin(String accountNumber, String inputPIN);

    boolean isValidAccountNumber(String accountNumber);

    boolean isExistedAccount(String accountNumber);
}
