package com.example.fraudcore.Model;

import java.util.Date;
import java.util.List;

public class TransactionDetailResponse {
    private int id;
    private String status;
    private Long amount;
    private String transactionType;
    private Date createdDate;
    private int customerId;
    private String customerName;
    private String customerMobile;
    private String flag;
    private List<FraudTransactionDetailResponse> fraudTransactionDetailResponses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<FraudTransactionDetailResponse> getFraudTransactionDetailResponses() {
        return fraudTransactionDetailResponses;
    }

    public void setFraudTransactionDetailResponses(List<FraudTransactionDetailResponse> fraudTransactionDetailResponses) {
        this.fraudTransactionDetailResponses = fraudTransactionDetailResponses;
    }
}
