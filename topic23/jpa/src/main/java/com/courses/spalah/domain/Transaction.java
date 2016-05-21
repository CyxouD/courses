package com.courses.spalah.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries(value={@NamedQuery(name="findTransactionByClientAccount", query="SELECT OBJECT(t) from Transaction as t where t.accountId = :accountId"), @NamedQuery(name="findTransactionByClientINN", query="SELECT Object(t) from Transaction t where t.accountId IN (SELECT a.id from Account a where a.clientInn = :clientINN)")})
@Table(name="transaction")
public class Transaction {
    private int id;
    private int accountId;
    private double sum;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    @Column(name="account", nullable=false)
    public int getAccountId() {
        return this.accountId;
    }

    @Column(name="sum", nullable=false)
    public double getSum() {
        return this.sum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String toString() {
        return "Transaction{id=" + this.id + ", accountId=" + this.accountId + ", sum=" + this.sum + '}';
    }
}