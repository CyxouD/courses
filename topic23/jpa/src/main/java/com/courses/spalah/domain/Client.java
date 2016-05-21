package com.courses.spalah.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="client")
public class Client {
    private int inn;
    private String name;
    private String surname;
    @OneToMany
    @JoinTable(name="accounts", joinColumns={@JoinColumn(name="INN")}, inverseJoinColumns={@JoinColumn(name="Client")})
    List<Account> accounts = null;

    @Id
    @Column(name="INN")
    public int getInn() {
        return this.inn;
    }

    @Column(name="Surname")
    public String getSurname() {
        return this.surname;
    }

    @Column(name="Name", nullable=false)
    public String getName() {
        return this.name;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
        return "Client{inn=" + this.inn + ", name='" + this.name + '\'' + ", surname='" + this.surname + '\'' + '}';
    }
}