package service.impl;

import model.Account;
import service.WithDrawService;
import utils.Utils;

public class WithDrawServiceImpl implements WithDrawService {

    @Override
    public boolean isValidWithDrawAmount(String withdrawAmount, int balance) {
        if (!withdrawAmount.matches("^[0-9]*$")) {
            System.out.println("Invalid amount");
            return false;
        }
        if (Integer.valueOf(withdrawAmount) > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            return false;
        }
        if (!(Integer.valueOf(withdrawAmount) % 10 == 0)) {
            System.out.println("Invalid amount");
            return false;
        }
        if (balance - Integer.valueOf(withdrawAmount) < 0) {
            System.out.println("Insufficient balance $" + withdrawAmount);
            return false;
        }
        return true;
    }

    @Override
    public Account withDraw(Account account, int withDraw) {
        Utils utils = new Utils();
        Integer intBalance = Integer.valueOf(utils.formatCurrency(account.getBalance().replaceAll("^\"|\"$", "")));
        account.setBalance("$" + (intBalance - withDraw));
        return account;
    }
}
