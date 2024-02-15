package com.example.fraudcore.Dao;

import com.example.fraudcore.Domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByCustomerId(int customerId);

}
