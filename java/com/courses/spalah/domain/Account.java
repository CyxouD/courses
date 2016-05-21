package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries(value={@NamedQuery(name="findAccountByClientINN", query="SELECT OBJECT(a) from Account a where a.clientInn = :clientINN")})
@Table(name="account")
public class Account {
    private int id;
    private int clientInn;
    private double balance;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    @Column(name="Client", nullable=0)
    public int getClientInn() {
        return this.clientInn;
    }

    @Column(name="balance", nullable=0)
    public double getBalance() {
        return this.balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientInn(int clientInn) {
        this.clientInn = clientInn;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Account{id=" + this.id + ", clientInn=" + this.clientInn + ", balance=" + this.balance + '}';
    }
}