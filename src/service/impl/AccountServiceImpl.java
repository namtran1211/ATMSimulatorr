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
        boolean bl = true;
        if (inputPIN.length() != 6) {
            System.out.println("PIN should have 6 digits length");
            bl = false;
        }
        if (!inputPIN.matches("^[0-9]*$")) {
            System.out.println("PIN should only contains numbers");
            bl = false;
        }
        Account accountByAccNumber = getAccountByAccNumber(accountNumber);
        if (!accountByAccNumber.getPin().equals(inputPIN)) {
            System.out.println("Invalid PIN Number");
        }
        return bl;
    }

    @Override
    public boolean isValidAccountNumber(String accountNumber) {
        boolean bl = true;
        if (accountNumber.length() != 6) {
            System.out.println("Account Number should have 6 digits length");
            bl = false;
        }
        if (!accountNumber.matches("^[0-9]*$")) {
            System.out.println("Account Number should only contains numbers");
            bl = false;
        }
        if (getAccountByAccNumber(accountNumber) == null) {
            System.out.println("Invalid Account Number");
            bl = false;
        }
        return bl;
    }
}
