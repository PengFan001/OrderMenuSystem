package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Worker {

    @Id
    private String accounts;
    private String password;

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "accounts='" + accounts + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
