package service.impl.v2;

import model.Account;
import service.AccountService;
import service.AccountValidationService;
import service.impl.AccountServiceImpl;

public class AccountValidationServiceImpl implements AccountValidationService {
    AccountService accountService = new AccountServiceImpl();

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
        Account accountByAccNumber = accountService.getAccountByAccNumber(accountNumber);
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
        Account accountByAccNumber = accountService.getAccountByAccNumber(accountNumber);
        if (accountByAccNumber == null) {
            System.out.println("Invalid account");
            return false;
        }
        return true;
    }
}
