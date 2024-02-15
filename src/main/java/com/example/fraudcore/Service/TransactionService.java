package com.example.fraudcore.Service;

import com.example.fraudcore.Domain.Transaction;
import com.example.fraudcore.Model.TransactionRequest;
import com.example.fraudcore.Model.TransactionResponse;

import java.util.List;

public interface TransactionService {

    public String createCustomer(String name, String nic);
    public TransactionResponse createTransaction(TransactionRequest transactionRequest);
    public List<Transaction> getAllTransactions();

}
