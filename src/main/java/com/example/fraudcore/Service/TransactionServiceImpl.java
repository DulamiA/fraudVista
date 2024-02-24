package com.example.fraudcore.Service;

import com.example.fraudcore.Dao.CustomerRepo;
import com.example.fraudcore.Dao.FraudTxnRepo;
import com.example.fraudcore.Dao.TransactionRepo;
import com.example.fraudcore.Domain.Customer;
import com.example.fraudcore.Domain.FraudTransaction;
import com.example.fraudcore.Domain.Transaction;
import com.example.fraudcore.Model.TransactionDetailResponse;
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

    @Autowired
    FraudTxnRepo fraudTxnRepo;

    @Autowired
    FraudService fraudService;

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

        Customer customer = customerRepo.getCustomerById(customerId);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setAmount(Long.parseLong(transactionRequest.getAmount()));
        transaction.setStatus(transactionRequest.getStatus());
        transaction.setCustomer(customer);
        transaction.setCreatedDate(new Date());

        transaction = transactionRepo.save(transaction);

        isFraud = fraudService.fraudChecker(transaction, customer);

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

    @Override
    public List<TransactionDetailResponse> getTransactionByCustomerId(int custId) throws Exception {
        Customer customer = customerRepo.getCustomerById(custId);
        if(customer==null){
            throw new Exception("Customer Not Found");
        }
        List<TransactionDetailResponse> transactionDetailResponses = new ArrayList<>();
        List<Transaction> transactions = transactionRepo.getTransactionsByCustomer(customer);
        for(Transaction transaction : transactions){
            TransactionDetailResponse response = new TransactionDetailResponse();
            response.setId(transaction.getId());
            response.setAmount(transaction.getAmount());
            response.setTransactionType(transaction.getTransactionType());
            response.setStatus(transaction.getStatus());
            response.setCustomerId(transaction.getCustomer().getId());
            response.setCustomerName(transaction.getCustomer().getFullName());
            response.setCreatedDate(transaction.getCreatedDate());
            response.setCustomerMobile(transaction.getCustomer().getMobile());
            transactionDetailResponses.add(response);
        }
        return transactionDetailResponses;

    }

    @Override
    public TransactionDetailResponse getTransactionById(int id) {
        Transaction transaction = transactionRepo.getTransactionById(id);
        TransactionDetailResponse response = new TransactionDetailResponse();
        response.setId(transaction.getId());
        response.setAmount(transaction.getAmount());
        response.setTransactionType(transaction.getTransactionType());
        response.setStatus(transaction.getStatus());
        response.setCustomerId(transaction.getCustomer().getId());
        response.setCustomerName(transaction.getCustomer().getFullName());
        response.setCreatedDate(transaction.getCreatedDate());
        response.setCustomerMobile(transaction.getCustomer().getMobile());

        return response;
    }
}
