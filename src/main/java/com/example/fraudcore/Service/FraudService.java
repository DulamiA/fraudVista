package com.example.fraudcore.Service;


import com.example.fraudcore.Domain.Customer;
import com.example.fraudcore.Domain.FraudRules;
import com.example.fraudcore.Domain.Transaction;
import com.example.fraudcore.Model.FlagTransactionRequest;
import com.example.fraudcore.Model.TransactionDetailResponse;
import com.example.fraudcore.Model.TransactionResponse;
import com.example.fraudcore.Model.TransactionStatResponse;

import java.util.List;

public interface FraudService {

    public boolean fraudChecker(Transaction transaction, Customer customer);
    public List<TransactionDetailResponse> getFraudTransactions();
    public TransactionResponse flagTransaction(FlagTransactionRequest flagTransactionRequest);
    public List<FraudRules> getAllFraudRules();
    public TransactionStatResponse getTransactionStat();
}
