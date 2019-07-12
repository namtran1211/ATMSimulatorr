package service;

import model.Account;

public interface TransferService {
    boolean isValidTransferAmount(String transferAmount, int balance);

    boolean isValidReferenceNumber(String referenceNumber);

    Account transfer(String destinationAccount, Account account, int transferAmount);
}
