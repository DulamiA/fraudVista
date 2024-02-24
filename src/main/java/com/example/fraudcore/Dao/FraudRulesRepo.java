package com.example.fraudcore.Dao;


import com.example.fraudcore.Domain.FraudRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraudRulesRepo extends JpaRepository<FraudRules, Long> {

    FraudRules getFraudRulesById(int id);

}
