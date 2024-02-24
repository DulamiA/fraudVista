package com.example.fraudcore.Dao;

import com.example.fraudcore.Domain.Customer;
import com.example.fraudcore.Domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByCustomer(Customer customer);
    List<Transaction> getTransactionsByCustomer(Customer customer);
    Transaction getTransactionById(int txnId);
    List<Transaction> findTransactionsByCustomerAndAmountAndCreatedDateIsBetween(Customer customer, Long amount, Date fromDate, Date toDate);

    List<Transaction> findTransactionsByCustomerAndCreatedDateBetween(Customer customer, Date fromDate, Date toDate);
    List<Transaction> getTransactionsByCreatedDateBetween(Date fromDate, Date todate);
}
