package ui;

import model.Account;

import java.util.Scanner;

class TransactionScreen {
    void transaction_menu(Account account) {
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("----Transaction Screen----");
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Exit");
            System.out.print("Please choose option[3]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    WithDrawScreen withDrawScreen = new WithDrawScreen();
                    withDrawScreen.withdraw_menu(account);
                    break;
                case 2:
                    FundTransferScreen transferScreen = new FundTransferScreen();
                    transferScreen.fund_transfer_account(account);
                    break;
                case 3:
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.welcome_menu();
                    break;
            }
        } while (choice != 3);

    }
}
