package com.example.fraudcore.Service;

import com.example.fraudcore.Dao.CustomerRepo;
import com.example.fraudcore.Dao.TransactionRepo;
import com.example.fraudcore.Domain.Customer;
import com.example.fraudcore.Domain.Transaction;
import com.example.fraudcore.Model.TransactionRequest;
import com.example.fraudcore.Model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public String createCustomer(String name, String nic) {
        Customer customer = new Customer();
        customer.setFullName(name);
        customer.setNic(nic);
        customerRepo.save(customer);
        return "Success";
    }

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {

        TransactionResponse response = new TransactionResponse();
        int customerId= Integer.parseInt(transactionRequest.getCustomerId());
        Boolean isFraud = false;

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setAmount(Long.parseLong(transactionRequest.getAmount()));
        transaction.setStatus(transactionRequest.getStatus());
        transaction.setCustomerId(customerId);
        transaction.setCreatedDate(new Date());

        List<Transaction> custTxn = transactionRepo.findTransactionByCustomerId(customerId);
        if(custTxn.size()>=5){
            isFraud = true;
        }

        /*
        * Check for fraud rules
        * */

        transactionRepo.save(transaction);
        response.setResponseCode("000");
        response.setResponseStatus("SUCCESS");
        response.setFraud(isFraud);
        return response;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> allTxns = new ArrayList<>();
        allTxns = transactionRepo.findAll();

        return allTxns;
    }
}
