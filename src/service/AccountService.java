package service;

import model.Account;

import java.util.List;

public interface AccountService {
    List<Account> listAccount();

    Account getAccountByAccNumber(String accNumber);

}
