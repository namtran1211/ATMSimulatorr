package ui;

import model.Account;

import java.util.Scanner;

class SummaryScreen {
    void summary_menu(Account account, String date, String withdraw) {
        System.out.println("----------------------------------------");
        System.out.println("----Summary----");
        System.out.println("Date  : " + date);
        System.out.println("Withdraw : " + withdraw);
        System.out.println("Balance : " + account.getBalance());

        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                TransactionScreen transactionScreen = new TransactionScreen();
                transactionScreen.transaction_menu(account);
            case 2:
                WelcomeScreen welcomeScreen = new WelcomeScreen();
                welcomeScreen.welcome_menu();
        }
    }
}
