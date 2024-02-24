package com.example.fraudcore.Service;

import com.example.fraudcore.Dao.FraudRulesRepo;
import com.example.fraudcore.Dao.FraudTxnRepo;
import com.example.fraudcore.Dao.TransactionRepo;
import com.example.fraudcore.Domain.Customer;
import com.example.fraudcore.Domain.FraudRules;
import com.example.fraudcore.Domain.FraudTransaction;
import com.example.fraudcore.Domain.Transaction;
import com.example.fraudcore.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FraudServiceImpl implements FraudService{

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    FraudRulesRepo fraudRulesRepo;

    @Autowired
    FraudTxnRepo fraudTxnRepo;

    @Override
    public boolean fraudChecker(Transaction transaction, Customer customer) {

        boolean isFraud = false;
        boolean fraudchecker = false;
        String severity ="L";

        List<FraudRules> fraudRulesList = fraudRulesRepo.findAll();
        for(FraudRules fraudRules : fraudRulesList){

            isFraud = false;

            String rule = fraudRules.getRule();
            int value = Integer.parseInt(fraudRules.getValue());
            Date toDate  = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date fromDate =  calendar.getTime();


            switch (rule){
                case "DAILY_TXN_LIMIT_EXCEEDED":
                    List<Transaction> customerTransaction1  = transactionRepo.findTransactionsByCustomerAndCreatedDateBetween(customer, fromDate, toDate);
                    if(customerTransaction1.size() > value){
                        isFraud = true;
                        severity = "L";
                    }
                    break;
                case("ABNORMAL_TXN_COUNT"):

                    System.out.println("FromDate: "+fromDate.toString() + " todate: "+ toDate.toString());
                    List<Transaction> customerTxn = transactionRepo.
                            findTransactionsByCustomerAndAmountAndCreatedDateIsBetween(customer,transaction.getAmount(),fromDate, toDate);
                    if(customerTxn.size() > value){
                        isFraud = true;
                        severity = "H";
                    }
                    break;
                case("ABNORMAL_TXN_FREQUENCY"):
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.set(Calendar.MINUTE, -59);
                    Date fromDate1 =  calendar1.getTime();
                    List<Transaction> customerTransaction2  = transactionRepo.findTransactionsByCustomerAndCreatedDateBetween(customer, fromDate1, toDate);
                    if(customerTransaction2.size() > value){
                        isFraud = true;
                        severity = "H";
                    }
                    break;
                case("ABNORMAL_CUSTOMER_TXN_COUNT"):
                    List<Transaction> custTxn = transactionRepo.findTransactionsByCustomer(customer);
                    if(custTxn.size()>=value){
                        isFraud = true;
                        severity = "M";
                    }
                    break;
            }
            if(isFraud){
                fraudchecker = isFraud;
                FraudTransaction fraudTransaction =  new FraudTransaction();
                fraudTransaction.setTransaction(transaction);
                fraudTransaction.setCreatedDate(new Date());
                fraudTransaction.setFlag("Y");
                fraudTransaction.setSeverity(severity);
                fraudTransaction.setFraudRules(fraudRules);
                fraudTxnRepo.save(fraudTransaction);
            }

        }

        return fraudchecker;

    }

    @Override
    public List<TransactionDetailResponse> getFraudTransactions() {

        List<TransactionDetailResponse> response = new ArrayList<>();

        List<FraudTransaction> fraudTransactions = fraudTxnRepo.findAll();
        for(FraudTransaction fraudTransaction : fraudTransactions){
            TransactionDetailResponse detailResponse = new TransactionDetailResponse();
            detailResponse.setId(fraudTransaction.getTransaction().getId());
            detailResponse.setAmount(fraudTransaction.getTransaction().getAmount());
            detailResponse.setTransactionType(fraudTransaction.getTransaction().getTransactionType());
            detailResponse.setStatus(fraudTransaction.getTransaction().getStatus());
            detailResponse.setCustomerId(fraudTransaction.getTransaction().getCustomer().getId());
            detailResponse.setCustomerName(fraudTransaction.getTransaction().getCustomer().getFullName());
            detailResponse.setCreatedDate(fraudTransaction.getTransaction().getCreatedDate());
            detailResponse.setCustomerMobile(fraudTransaction.getTransaction().getCustomer().getMobile());
            Transaction transaction = fraudTransaction.getTransaction();
            List<FraudTransaction> fraudTransactions1 = fraudTxnRepo.findAllByTransaction(transaction);
            List<FraudTransactionDetailResponse> detailResponseList = new ArrayList<>();
            for(FraudTransaction fraudTransaction1 : fraudTransactions1){
                FraudTransactionDetailResponse fraudTransactionDetailResponse = new FraudTransactionDetailResponse();
                fraudTransactionDetailResponse.setFraudRule(fraudTransaction1.getFraudRules()!= null? fraudTransaction1.getFraudRules().getRule():"N/A");
                fraudTransactionDetailResponse.setRemark(fraudTransaction1.getRemark());
                fraudTransactionDetailResponse.setFlag(fraudTransaction1.getFlag());
                fraudTransactionDetailResponse.setModifiedBy(fraudTransaction1.getModifiedBy());
                fraudTransactionDetailResponse.setSeverity(fraudTransaction1.getSeverity());
                fraudTransactionDetailResponse.setModifiedBy(fraudTransaction1.getModifiedBy());
                detailResponseList.add(fraudTransactionDetailResponse);
            }
            detailResponse.setFraudTransactionDetailResponses(detailResponseList);
            response.add(detailResponse);
        }

        return response;
    }

    @Override
    public TransactionResponse flagTransaction(FlagTransactionRequest flagTransactionRequest) {
        Transaction transaction = transactionRepo.getTransactionById(flagTransactionRequest.getTransactionId());
        FraudRules fraudRules = fraudRulesRepo.getFraudRulesById(flagTransactionRequest.getFraudRule());

        FraudTransaction fraudTransaction = new FraudTransaction();
        fraudTransaction.setTransaction(transaction);
        fraudTransaction.setFraudRules(fraudRules);
        fraudTransaction.setRemark(flagTransactionRequest.getRemark());
        fraudTransaction.setSeverity(flagTransactionRequest.getSeverity());
        fraudTransaction.setFlag(flagTransactionRequest.getFlag());
        fraudTransaction.setCreatedDate(new Date());
        fraudTransaction.setModifiedBy(flagTransactionRequest.getModifiedBy());

        fraudTxnRepo.save(fraudTransaction);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setFraud(true);
        transactionResponse.setResponseCode("000");
        transactionResponse.setResponseStatus("SUCCESS");

        return transactionResponse;

    }

    @Override
    public List<FraudRules> getAllFraudRules() {
        return fraudRulesRepo.findAll();
    }

    @Override
    public TransactionStatResponse getTransactionStat() {

        Date toDate  = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date fromDate =  calendar.getTime();

        TransactionStatResponse transactionStatResponse = new TransactionStatResponse();
        List<Transaction> transactions = transactionRepo.getTransactionsByCreatedDateBetween(fromDate, toDate);
        List<FraudTransaction> fraudTransactions = fraudTxnRepo.findAllByCreatedDateBetween(fromDate, toDate);

        transactionStatResponse.setTotalTransactionCount(transactions.size());
        transactionStatResponse.setTotFraudTransactionCount(fraudTransactions != null? fraudTransactions.size() : 0);

        return transactionStatResponse;
    }
}
