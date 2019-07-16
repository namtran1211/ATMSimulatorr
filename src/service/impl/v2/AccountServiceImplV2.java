package service.impl.v2;

import model.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImplV2 implements AccountServiceV2 {

    private static final String PATH_FILE = "C:" + File.separator + "Users" + File.separator + "NAM" + File.separator + "Desktop";
    private static final String FILE_NAME = "Book1.csv";

    @Override
    public List<Account> listAccount() {
        return listAllAccountFromFile(FILE_NAME);
    }

    @Override
    public Account getAccountByAccNumber(String accNumber) {
        List<Account> accounts = listAccount();
        return accounts.stream().filter(account -> account.getAccountNumber().equals(accNumber)).findAny().orElse(null);
    }

    private List<Account> listAllAccountFromFile(String fileName) {
        File file = new File(PATH_FILE + File.separator + fileName);
        InputStream inputStream;
        List<Account> collect = new ArrayList<>();
        try {
            inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            collect = reader.lines().skip(1).map(s -> {
                Account account = new Account();
                String[] line = s.split(",");
                account.setName(line[0]);
                account.setPin(line[1]);
                account.setBalance(line[2]);
                account.setAccountNumber(line[3]);
                return account;
            }).collect(Collectors.toList());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collect;
    }

    @Override
    public void updateAccountBalance(String accountNumber, String balance, String fileName) {
//        File file = new File(PATH_FILE + File.separator + fileName);
//        InputStream inputStream;
//        OutputStream outputStream;
//        List<Account> collect = new ArrayList<>();
//        try {
//            inputStream = new FileInputStream(file);
//            outputStream = new FileOutputStream(file);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//            reader.lines().skip(1).map(s -> {
//                String[] split = s.split(",");
//                if (split[3].equals(accountNumber)) {
//                    try {
//                        writer.write(balance);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).collect(Collectors.toList());
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
