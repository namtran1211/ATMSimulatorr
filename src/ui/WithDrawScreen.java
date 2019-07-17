package ui;

import model.Account;
import service.WithDrawService;
import service.impl.WithDrawServiceImpl;
import service.impl.v2.AccountServiceImplV2;
import service.impl.v2.AccountServiceV2;
import utils.Constant;
import utils.Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

class WithDrawScreen {
    private WithDrawService withDrawService = new WithDrawServiceImpl();
    private AccountServiceV2 accountService = new AccountServiceImplV2();

    void withdraw_menu(Account account) {
        String choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("----WithDraw Screen----");
            System.out.println("1. $10");
            System.out.println("2. $50");
            System.out.println("3. $100");
            System.out.println("4. Other");
            System.out.println("5. Back");
            System.out.print("Please choose option[5]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    if (isValidWithDraw(account, "10")) {
                        withDraw(account, 10);
                    }
                    break;
                case "2":
                    if (isValidWithDraw(account, "10")) {
                        withDraw(account, 50);
                    }
                    break;
                case "3":
                    if (isValidWithDraw(account, "100")) {
                        withDraw(account, 100);
                    }
                    break;
                case "4":
                    withdraw_other_menu(account);
                    break;
                case "":
                case "5":
                    TransactionScreen transactionScreen = new TransactionScreen();
                    transactionScreen.transaction_menu(account);
                    break;
            }
        } while (!choice.equals("5"));
    }

    private void withdraw_other_menu(Account account) {
        boolean validWithDraw;
        do {
            System.out.print("Other Withdraw\n" +
                    "Enter amount to withdraw: ");
            Scanner scanner = new Scanner(System.in);
            String otherWithDraw = scanner.next();
            validWithDraw = isValidWithDraw(account, otherWithDraw);
            if (validWithDraw) {
                withDraw(account, Integer.valueOf(otherWithDraw));
            }
        } while (!validWithDraw);
    }

    private void withDraw(Account account, int withDraw) {
        Account account1 = withDrawService.withDraw(account, withDraw);
        try {
            accountService.updateAccountBalance(account.getAccountNumber(), account.getBalance(), Constant.FILE_NAME);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot read csv file");
        }
        SummaryScreen summaryScreen = new SummaryScreen();
        summaryScreen.summary_menu(account1, LocalDateTime.now().toString(), "$" + withDraw);
    }

    private boolean isValidWithDraw(Account account, String withDraw) {
        Utils utils = new Utils();
        int balance = Integer.valueOf(utils.formatCurrency(account.getBalance().replaceAll("^\"|\"$", "")));
        return withDrawService.isValidWithDrawAmount(withDraw, balance);
    }
}
