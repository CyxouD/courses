package com.courses.spalah;

import com.courses.spalah.domain.Account;
import com.courses.spalah.domain.Client;
import com.courses.spalah.domain.Transaction;

public class BankCommandProcessor {
    private final int NUMBER_ADD_ACCOUNT_MAX_PARAM = 2;

    public Client formClientToAdd(String inn, String name, String surname) {
        Client client = new Client();
        client.setInn(Integer.parseInt(inn));
        client.setName(name);
        client.setSurname(surname);
        return client;
    }

    public Account formAccountToAdd(String clientINN, String balance) {
        Account account = new Account();
        account.setClientInn(Integer.parseInt(clientINN));
        account.setBalance(Double.parseDouble(balance));
        return account;
    }

    public Transaction formTransactionToAdd(String accountId, String transactionSum) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(Integer.parseInt(accountId));
        transaction.setSum(Double.parseDouble(transactionSum));
        return transaction;
    }
}