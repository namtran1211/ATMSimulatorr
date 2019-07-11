package ui;

import model.Account;
import service.WithDrawService;
import service.impl.WithDrawServiceImpl;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.Scanner;

class WithDrawScreen {
    private WithDrawService withDrawService = new WithDrawServiceImpl();

    void withdraw_menu(Account account) {
        int choice;
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
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    withDraw(account, 10);
                    break;
                case 2:
                    withDraw(account, 50);
                    break;
                case 3:
                    withDraw(account, 100);
                    break;
                case 4:
                    withdraw_other_menu(account);
                    break;
                case 5:
                    TransactionScreen transactionScreen = new TransactionScreen();
                    transactionScreen.transaction_menu(account);
                    break;
            }
        } while (choice != 5);
    }

    private void withdraw_other_menu(Account account) {
        boolean validWithDraw;
        do {
            System.out.print("Other Withdraw\n" +
                    "Enter amount to withdraw: ");
            Scanner scanner = new Scanner(System.in);
            String otherWithDraw = scanner.next();
            validWithDraw = withDraw(account, Integer.valueOf(otherWithDraw));
        } while (!validWithDraw);
    }

    private boolean withDraw(Account account, int withDraw) {
        SummaryScreen summaryScreen = new SummaryScreen();
        Utils utils = new Utils();
        int balance = Integer.valueOf(utils.formatCurrency(account.getBalance()));
        boolean isValidWithDraw = withDrawService.isValidWithDrawAmount(withDraw, balance);
        if (isValidWithDraw) {
            Account account1 = withDrawService.withDraw(account, withDraw);
            summaryScreen.summary_menu(account1, LocalDateTime.now().toString(), "$" + withDraw);
        }
        return isValidWithDraw;
    }

}
