package com.example.fraudcore.Controller;

import com.example.fraudcore.Domain.FraudRules;
import com.example.fraudcore.Model.FlagTransactionRequest;
import com.example.fraudcore.Model.TransactionDetailResponse;
import com.example.fraudcore.Model.TransactionResponse;
import com.example.fraudcore.Model.TransactionStatResponse;
import com.example.fraudcore.Service.FraudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FraudTransactionController {

    @Autowired
    FraudService fraudService;

    @PutMapping("/fraud/transaction/flag")
    public TransactionResponse flagTransaction(@RequestBody FlagTransactionRequest flagTransactionRequest){
        return fraudService.flagTransaction(flagTransactionRequest);
    }

    @GetMapping("/fraud/rules")
    public List<FraudRules> getAllFraudRules(){
        return fraudService.getAllFraudRules();
    }

    @GetMapping("/fraud/transaction")
    public List<TransactionDetailResponse> getAllFraudTransactionDetails(){
        return fraudService.getFraudTransactions();
    }

    @GetMapping("/fraud/transaction/stat")
    public TransactionStatResponse getTransactionStats(){
        return fraudService.getTransactionStat();
    }


}
