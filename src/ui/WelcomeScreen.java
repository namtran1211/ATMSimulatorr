package ui;

import model.Account;
import service.AccountService;
import service.impl.AccountServiceImpl;

import java.util.Scanner;

public class WelcomeScreen {
    private AccountService accountService = new AccountServiceImpl();
    public void welcome_menu() {
        boolean bl;
        Scanner scanner = new Scanner(System.in);
        String accountInput;
        do {
            System.out.println("----------------------------------------");
            System.out.println("-----Welcome-----");
            System.out.print("Enter Account Number: ");
            accountInput = scanner.next();
            bl = accountService.isValidAccountNumber(accountInput);
        } while (!bl);
        do {
            System.out.print("Enter PIN: ");
            String pinInput = scanner.next();
            bl = accountService.isValidPin(accountInput, pinInput);
        } while (!bl);
        TransactionScreen transactionScreen = new TransactionScreen();
        Account accountByAccNumber = accountService.getAccountByAccNumber(accountInput);
        transactionScreen.transaction_menu(accountByAccNumber);
    }
}
