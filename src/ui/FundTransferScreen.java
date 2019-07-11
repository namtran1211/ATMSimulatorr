package ui;

import model.Account;
import service.AccountService;
import service.TransferService;
import service.impl.AccountServiceImpl;
import service.impl.TransferServiceImpl;
import utils.Utils;

import java.util.Scanner;

class FundTransferScreen {
    private AccountService accountService = new AccountServiceImpl();
    private TransferService transferService = new TransferServiceImpl();

    void fund_transfer_account(Account account) {
        boolean validAccountNumber;
        do {
            System.out.println("----------------------------------------");
            System.out.print("Please enter destination account and \n" +
                    "press enter to continue or \n" +
                    "press cancel (Esc) to go back to Transaction: ");
            Scanner scanner = new Scanner(System.in);
            String destinationAccount = scanner.next();
            validAccountNumber = accountService.isValidAccountNumber(destinationAccount);
            if (validAccountNumber) {
                fund_transfer_transferAmount(account, destinationAccount);
            }
        } while (!validAccountNumber);

    }

    private void fund_transfer_transferAmount(Account account, String destination) {
        boolean validTransferAmount;
        do {
            System.out.println("----------------------------------------");
            System.out.print("Please enter transfer amount and \n" +
                    "press enter to continue or \n" +
                    "press cancel (Esc) to go back to Transaction: ");
            Scanner scanner = new Scanner(System.in);
            String transferAmount = scanner.next();
            Utils utils = new Utils();
            int balance = Integer.valueOf(utils.formatCurrency(account.getBalance()));
            validTransferAmount = transferService.isValidTransferAmount(Integer.valueOf(transferAmount), balance);
            if (validTransferAmount) {
                fund_transfer_referenceNumber(destination, Integer.valueOf(transferAmount), account);
            }
        } while (!validTransferAmount);
    }

    private void fund_transfer_referenceNumber(String destination, int transferAmount, Account account) {
        boolean validReferenceNumber;
        do {
            System.out.println("----------------------------------------");
            System.out.print("Please enter reference number (Optional) and \n" +
                    "press enter to continue or \n" +
                    "press cancel (Esc) to go back to Transaction: ");
            Scanner scanner = new Scanner(System.in);
            String referenceId = scanner.next();
            validReferenceNumber = transferService.isValidReferenceNumber(referenceId);
            if (validReferenceNumber) {
                fund_transfer_confirm(destination, transferAmount, referenceId, account);
            }
        } while (!validReferenceNumber);

    }

    private void fund_transfer_confirm(String destination, int transferAmount, String referenceNumber, Account account) {
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Transfer Confirmation");
            System.out.println("Destination model.Account : " + destination);
            System.out.println("Transfer Amount : $" + transferAmount);
            System.out.println("Reference Number : " + referenceNumber);
            System.out.println("1. Confirm Trx");
            System.out.println("2. Cancel Trx");
            System.out.print("Choose option[2]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Account transferAccount = transferService.transfer(destination, account, transferAmount);
                    fund_transfer_summary(destination, transferAmount, referenceNumber, transferAccount);
                case 2:
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.welcome_menu();
            }
        } while (choice != 2);
    }

    private void fund_transfer_summary(String destination, int transferAmount, String referenceNumber, Account account) {
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Fund Transfer Summary");
            System.out.println("Destination model.Account : " + destination);
            System.out.println("Transfer Amount : $" + transferAmount);
            System.out.println("Reference Number : " + referenceNumber);
            System.out.println("Balance : " + account.getBalance());
            System.out.println("1. Transaction");
            System.out.println("2. Exit");
            System.out.print("Choose option[2]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    TransactionScreen transactionScreen = new TransactionScreen();
                    transactionScreen.transaction_menu(account);
                case 2:
                    System.exit(0);
            }
        } while (true);
    }
}
