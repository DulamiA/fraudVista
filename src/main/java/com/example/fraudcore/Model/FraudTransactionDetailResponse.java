package com.example.fraudcore.Model;

import java.util.Date;

public class FraudTransactionDetailResponse {

    private String fraudRule;
    private String ruleDescription;
    private String remark;
    private String severity;
    private String flag;
    private String modifiedBy;

    public String getFraudRule() {
        return fraudRule;
    }

    public void setFraudRule(String fraudRule) {
        this.fraudRule = fraudRule;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
