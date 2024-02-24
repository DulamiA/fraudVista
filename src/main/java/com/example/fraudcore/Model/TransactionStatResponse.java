package com.example.fraudcore.Model;

import java.util.Map;

public class TransactionStatResponse {

    private int totalTransactionCount;
    private int totFraudTransactionCount;
    private Map<String, String> fraudRuleWiseCount;
    private Map<String, String> severityWiseCount;

    public int getTotalTransactionCount() {
        return totalTransactionCount;
    }

    public void setTotalTransactionCount(int totalTransactionCount) {
        this.totalTransactionCount = totalTransactionCount;
    }

    public int getTotFraudTransactionCount() {
        return totFraudTransactionCount;
    }

    public void setTotFraudTransactionCount(int totFraudTransactionCount) {
        this.totFraudTransactionCount = totFraudTransactionCount;
    }

    public Map<String, String> getFraudRuleWiseCount() {
        return fraudRuleWiseCount;
    }

    public void setFraudRuleWiseCount(Map<String, String> fraudRuleWiseCount) {
        this.fraudRuleWiseCount = fraudRuleWiseCount;
    }

    public Map<String, String> getSeverityWiseCount() {
        return severityWiseCount;
    }

    public void setSeverityWiseCount(Map<String, String> severityWiseCount) {
        this.severityWiseCount = severityWiseCount;
    }
}
