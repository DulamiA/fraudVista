package com.example.fraudcore.Dao;


import com.example.fraudcore.Domain.FraudTransaction;
import com.example.fraudcore.Domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FraudTxnRepo extends JpaRepository<FraudTransaction, Long> {

    List<FraudTransaction> findAllByTransaction(Transaction transaction);
    List<FraudTransaction> findAllByCreatedDateBetween(Date fromdate, Date toDate);
}
