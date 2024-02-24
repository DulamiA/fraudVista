package com.example.fraudcore.Model;

public class FlagTransactionRequest {

    private String remark;
    private String modifiedBy;
    private String flag;
    private int transactionId;
    private int fraudRule;
    private String severity;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getFraudRule() {
        return fraudRule;
    }

    public void setFraudRule(int fraudRule) {
        this.fraudRule = fraudRule;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
