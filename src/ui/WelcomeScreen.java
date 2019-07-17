package ui;

import model.Account;
import service.AccountValidationService;
import service.impl.v2.AccountServiceImplV2;
import service.impl.v2.AccountServiceV2;
import service.impl.v2.AccountValidationServiceImpl;

import java.util.Scanner;

public class WelcomeScreen {
    private AccountServiceV2 accountService = new AccountServiceImplV2();
    private AccountValidationService validationService = new AccountValidationServiceImpl();

    public void welcome_menu() {
        boolean validAccountNumber;
        boolean validPin;
        Scanner scanner = new Scanner(System.in);
        String accountInput;
        do {
            System.out.println("----------------------------------------");
            System.out.println("-----Welcome-----");
            System.out.print("Enter Account Number: ");
            accountInput = scanner.next();
            System.out.print("Enter PIN: ");
            String pinInput = scanner.next();
            validAccountNumber = validationService.isValidAccountNumber(accountInput);
            validPin = validationService.isValidPin(accountInput, pinInput);
        } while (!(validPin && validAccountNumber));
        TransactionScreen transactionScreen = new TransactionScreen();
        Account accountByAccNumber = accountService.getAccountByAccNumber(accountInput);
        transactionScreen.transaction_menu(accountByAccNumber);
    }
}
