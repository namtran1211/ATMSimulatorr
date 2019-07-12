package ui;

import model.Account;

import java.util.Scanner;

class TransactionScreen {
    void transaction_menu(Account account) {
        String choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("----Transaction Screen----");
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Exit");
            System.out.print("Please choose option[3]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            switch (choice) {
                case "1":
                    WithDrawScreen withDrawScreen = new WithDrawScreen();
                    withDrawScreen.withdraw_menu(account);
                    break;
                case "2":
                    FundTransferScreen transferScreen = new FundTransferScreen();
                    transferScreen.fund_transfer_account(account);
                    break;
                case "":
                case "3":
                    welcomeScreen.welcome_menu();
                    break;
            }
        } while (!choice.equals("3"));

    }
}
