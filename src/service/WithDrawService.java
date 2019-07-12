package service;

import model.Account;

public interface WithDrawService {
    boolean isValidWithDrawAmount(String withdrawAmount, int balance);

    Account withDraw(Account account, int withDraw);
}
