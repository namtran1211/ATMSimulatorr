package service.impl;

import model.Account;
import service.AccountService;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> createAccount() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Jane Doe", "932012", "$30", "112244"));
        accounts.add(new Account("John Doe", "012108", "$100", "112233"));
        return accounts;
    }

    @Override
    public Account getAccountByAccNumber(String accNumber) {
        List<Account> accounts = createAccount();
        return accounts.stream().filter(account -> account.getAccountNumber().equals(accNumber)).findAny().orElse(null);
    }

    @Override
    public boolean isValidPin(String accountNumber, String inputPIN) {
        if (inputPIN.length() != 6) {
            System.out.println("PIN should have 6 digits length");
            return false;
        }
        if (!inputPIN.matches("^[0-9]*$")) {
            System.out.println("PIN should only contains numbers");
            return false;
        }
        Account accountByAccNumber = getAccountByAccNumber(accountNumber);
        if (accountByAccNumber == null || !accountByAccNumber.getPin().equals(inputPIN)) {
            System.out.println("Invalid Account Number/PIN");
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber.length() != 6) {
            System.out.println("Account Number should have 6 digits length");
            return false;
        }
        if (!accountNumber.matches("^[0-9]*$")) {
            System.out.println("Account Number should only contains numbers");
            return false;
        }
        return true;
    }

    @Override
    public boolean isExistedAccount(String accountNumber) {
        Account accountByAccNumber = getAccountByAccNumber(accountNumber);
        if (accountByAccNumber == null) {
            System.out.println("Invalid account");
            return false;
        }
        return true;
    }
}
