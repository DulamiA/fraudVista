package com.example.fraudcore.Domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FraudTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date createdDate;
    private String flag;
    private String remark;
    private String severity;
    private Date modifiedDate;
    private String modifiedBy;

    @ManyToOne
    private FraudRules fraudRules;

    @ManyToOne
    private Transaction transaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public FraudRules getFraudRules() {
        return fraudRules;
    }

    public void setFraudRules(FraudRules fraudRules) {
        this.fraudRules = fraudRules;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
