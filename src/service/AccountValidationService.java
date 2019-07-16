package service;

public interface AccountValidationService {

    boolean isValidPin(String accountNumber, String inputPIN);

    boolean isValidAccountNumber(String accountNumber);

    boolean isExistedAccount(String accountNumber);
}
