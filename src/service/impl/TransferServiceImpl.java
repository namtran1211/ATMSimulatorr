package service.impl;

import model.Account;
import service.TransferService;
import service.impl.v2.AccountServiceImplV2;
import service.impl.v2.AccountServiceV2;
import utils.Constant;
import utils.Utils;

import java.io.IOException;

public class TransferServiceImpl implements TransferService {
    private AccountServiceV2 accountServiceV2 = new AccountServiceImplV2();

    @Override
    public boolean isValidTransferAmount(String transferAmount, int balance) {
        if (!transferAmount.matches("^[0-9]*$")) {
            System.out.println("Invalid amount");
            return false;
        }
        if (Integer.valueOf(transferAmount) > 1000) {
            System.out.println("Maximum amount to transfer is $1000");
            return false;
        }

        if (Integer.valueOf(transferAmount) < 1) {
            System.out.println("Minimum  amount to transfer is $1");
            return false;
        }
        if (balance - Integer.valueOf(transferAmount) < 0) {
            System.out.println("Insufficient balance $" + transferAmount);
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidReferenceNumber(String referenceNumber) {
        if (!referenceNumber.isEmpty() && !referenceNumber.matches("^[0-9]*$")) {
            System.out.println("Invalid Reference Number");
            return false;
        }
        return true;
    }

    @Override
    public Account transfer(String destinationAccount, Account account, int transferAmount) {
        Utils utils = new Utils();
        Integer balance = Integer.valueOf(utils.formatCurrency(account.getBalance().replaceAll("^\"|\"$", "")));
        account.setBalance("$" + (balance - transferAmount));
        try {
            accountServiceV2.updateAccountBalance(account.getAccountNumber(), account.getBalance(), Constant.FILE_NAME);
        } catch (IOException e) {
            System.out.print("cannot read csv file");
        }
        return account;
    }
}
